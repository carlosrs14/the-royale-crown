package com.crinc.microservice_game.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MastermindResponseDTO {
    private Long id;
    // TODO make two response to not send combination to client
    private String combination;
    private String status;
    private List<AttemptResponseDTO> attempts;
}
