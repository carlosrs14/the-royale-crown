package com.crinc.microservice_game.services;

import java.util.List;

import com.crinc.microservice_game.dtos.request.AttemptRequestDTO;
import com.crinc.microservice_game.dtos.response.AttemptResponseDTO;

public interface AttemptService {
    AttemptResponseDTO create(AttemptRequestDTO attemptRequestDTO);
    AttemptResponseDTO findById(Long id);
    List<AttemptResponseDTO> findAll();
    List<AttemptResponseDTO> findByMastermaindId(Long mastermaindId);
    void delete(Long id);
}
