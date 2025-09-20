package com.crinc.microservice_game.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.crinc.microservice_game.dtos.request.MastermindRequestDTO;
import com.crinc.microservice_game.dtos.response.MastermindResponseDTO;
import com.crinc.microservice_game.exceptions.ResourceNotFoundException;
import com.crinc.microservice_game.mappers.MastermindMapper;
import com.crinc.microservice_game.models.Mastermind;
import com.crinc.microservice_game.models.MastermindStatus;
import com.crinc.microservice_game.repositories.MastermindRepository;
import com.crinc.microservice_game.services.MastermindService;

import jakarta.validation.Valid;

@Service
@Validated
public class MastermindServiceImpl implements MastermindService {

    final MastermindRepository mastermaindRepository;

    final MastermindMapper mastermaindMapper;

    MastermindServiceImpl(MastermindRepository mastermaindRepository, MastermindMapper mastermaindMapper) {
        this.mastermaindRepository = mastermaindRepository;
        this.mastermaindMapper = mastermaindMapper;
    }

    @Override
    public MastermindResponseDTO create(@Valid MastermindRequestDTO mastermaindRequestDTO) {
        Mastermind mastermaind = mastermaindMapper.toEntity(mastermaindRequestDTO);
        mastermaind.setStatus(MastermindStatus.PLAYING);
        // TODO add dificult
        mastermaind.setCombination(this.generateCombination(4));
        mastermaind = mastermaindRepository.save(mastermaind);
        return mastermaindMapper.toDto(mastermaind);
    }

    private String generateCombination(int length) {
        String combination = "";
        for (int i = 0; i < length; i++) {
            Integer random = (int) (Math.random() * 10);
            if (combination.contains(random.toString())) {
                i--;
                continue;
            }
            combination += random;
        }
        return combination;
    }


    @Override
    public MastermindResponseDTO findById(Long id) {
        Mastermind mastermaind = mastermaindRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Game Mastermaind", id));
        return mastermaindMapper.toDto(mastermaind);
    }

    @Override
    public List<MastermindResponseDTO> findAll() {
        List<Mastermind> mastermainds = (List<Mastermind>) mastermaindRepository.findAll();
        return mastermainds
            .stream()
            .map(mastermaindMapper::toDto)
            .collect(Collectors.toList());
    }

    
    @Override
    public MastermindResponseDTO updateStatus(Long id, MastermindStatus status) {
        Mastermind mastermind = mastermaindRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Game Mastermaind", id));
        mastermind.setStatus(status);
        mastermind = mastermaindRepository.save(mastermind);
        return mastermaindMapper.toDto(mastermind);
    }

    @Override
    public MastermindResponseDTO abandone(Long id) {
        Mastermind mastermind = mastermaindRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Game Mastermaind", id));
        mastermind.setStatus(MastermindStatus.ABANDONED);
        mastermind = mastermaindRepository.save(mastermind);
        return mastermaindMapper.toDto(mastermind);
    }

    @Override
    public Boolean isPlayable(Long id) {
        Mastermind mastermind = mastermaindRepository.findById(id)
            .orElseThrow();
        return mastermind.getStatus() == MastermindStatus.PLAYING;
    }

    @Override
    public void delete(Long id) {
        if (!mastermaindRepository.existsById(id)) {
            throw new ResourceNotFoundException("Game Mastermaind", id);
        } 
        mastermaindRepository.deleteById(id);
    }

    
}
