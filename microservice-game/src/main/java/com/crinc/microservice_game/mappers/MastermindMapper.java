package com.crinc.microservice_game.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.crinc.microservice_game.dtos.request.MastermindRequestDTO;
import com.crinc.microservice_game.dtos.response.MastermindResponseDTO;
import com.crinc.microservice_game.models.Mastermind;

@Mapper(componentModel = "spring")
public interface MastermindMapper {
    
    @Mapping(target = "attempts", ignore = true)
    MastermindResponseDTO toDto(Mastermind mastermaind);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "combination", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "attempts", ignore = true)
    Mastermind toEntity(MastermindRequestDTO mastermaindRequestDTO);
}
