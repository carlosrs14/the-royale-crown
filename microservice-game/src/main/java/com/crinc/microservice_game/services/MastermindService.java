package com.crinc.microservice_game.services;

import java.util.List;

import com.crinc.microservice_game.dtos.request.MastermindRequestDTO;
import com.crinc.microservice_game.dtos.response.MastermindResponseDTO;
import com.crinc.microservice_game.models.MastermindStatus;

public interface MastermindService {
    MastermindResponseDTO create(MastermindRequestDTO mastermaindRequestDTO);
    MastermindResponseDTO findById(Long id);
    List<MastermindResponseDTO> findAll();
    MastermindResponseDTO updateStatus(Long id, MastermindStatus status);
    MastermindResponseDTO abandone(Long id);
    Boolean isPlayable(Long id);
    void delete(Long id);
}
