package com.crinc.microservice_game.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.crinc.microservice_game.dtos.request.AttemptRequestDTO;
import com.crinc.microservice_game.dtos.response.AttemptResponseDTO;
import com.crinc.microservice_game.models.Attempt;

@Mapper(componentModel = "spring")
public interface AttemptMapper {
    @Mapping(target = "mastermaindId", source = "mastermaind.id")
    AttemptResponseDTO toDto(Attempt attempt);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attemptNumber", ignore = true)
    @Mapping(target = "mastermaind.id", source = "mastermaindId")
    @Mapping(target = "bulls", ignore = true)
    @Mapping(target = "cows", ignore = true)
    Attempt toEntity(AttemptRequestDTO attemptRequestDTO);
}
