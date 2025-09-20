package com.crinc.microservice_game.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.crinc.microservice_game.dtos.request.AttemptRequestDTO;
import com.crinc.microservice_game.dtos.response.AttemptResponseDTO;
import com.crinc.microservice_game.dtos.response.MastermindResponseDTO;
import com.crinc.microservice_game.exceptions.InvalidGuessExeption;
import com.crinc.microservice_game.exceptions.NotPlayableException;
import com.crinc.microservice_game.exceptions.ResourceNotFoundException;
import com.crinc.microservice_game.mappers.AttemptMapper;
import com.crinc.microservice_game.models.Attempt;
import com.crinc.microservice_game.models.MastermindStatus;
import com.crinc.microservice_game.repositories.AttemptRepository;
import com.crinc.microservice_game.services.AttemptService;
import com.crinc.microservice_game.services.MastermindService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Validated
public class AttemptServiceImpl implements AttemptService {

    private final AttemptRepository attemptRepository;

    private final AttemptMapper attemptMapper;

    private final MastermindService mastermindService;


    AttemptServiceImpl(AttemptRepository attemptRepository, AttemptMapper attemptMapper, MastermindService mastermindService) {
        this.attemptRepository = attemptRepository;
        this.attemptMapper = attemptMapper;
        this.mastermindService = mastermindService;
    }

    @Override
    @Transactional
    public AttemptResponseDTO create(@Valid AttemptRequestDTO attemptRequestDTO) {
        MastermindResponseDTO mastermind = mastermindService.findById(attemptRequestDTO.getMastermaindId());
        if (!mastermindService.isPlayable(attemptRequestDTO.getMastermaindId())) {
            throw new NotPlayableException(mastermind.getStatus().toString());
        }

        String combination = mastermind.getCombination();
        String guess = attemptRequestDTO.getGuess();

        if (combination.length() != guess.length()) {
            throw new InvalidGuessExeption("Number must have " + combination.length() + " digits");
        }

        if (haveRepeatedNumbers(guess)) {
            throw new InvalidGuessExeption("Number must not have repeated digits");
        }

        

        Attempt attempt = attemptMapper.toEntity(attemptRequestDTO);
        attempt.setAttemptNumber(mastermind.getAttempts().size() + 1);

        Result result = calculateResult(combination, attemptRequestDTO.getGuess());


        attempt.setBulls(result.getBulls());
        attempt.setCows(result.getCows());

        if (result.getBulls() == combination.length()) {
            mastermindService.updateStatus(attemptRequestDTO.getMastermaindId(), MastermindStatus.WON);
        }
        
        attempt = attemptRepository.save(attempt);
        return attemptMapper.toDto(attempt);
    }

    @Override
    public AttemptResponseDTO findById(Long id) {
        Attempt attempt = attemptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Attempt", id));
        return attemptMapper.toDto(attempt);
    }

    @Override
    public List<AttemptResponseDTO> findAll() {
        List<Attempt> attempts = (List<Attempt>) attemptRepository.findAll();
        return attempts
            .stream()
            .map(attemptMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<AttemptResponseDTO> findByMastermaindId(Long mastermaindId) {
        List<Attempt> attempts = attemptRepository.findByMastermaindId(mastermaindId);
        return attempts
                .stream()
                .map(attemptMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!attemptRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attempt", id);
        } 
        attemptRepository.deleteById(id);
    }

    private Result calculateResult(String combination, String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == combination.charAt(i)) {
                bulls++;
                continue;
            }
            if (combination.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }
        return new Result(bulls, cows);
    }

    private Boolean haveRepeatedNumbers(String guess) {
        Set<Character> hash = new HashSet<>();
        for (int i = 0; i < guess.length(); i++) {
            hash.add(guess.charAt(i));
        }
        return hash.size() != guess.length();
    }

    @Data
    @AllArgsConstructor
    public class Result {
        private int bulls;
        private int cows;
    }
}
