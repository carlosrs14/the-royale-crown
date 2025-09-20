package com.crinc.microservice_game.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptResponseDTO {
    private Long id;
    private String guess;
    private int attemptNumber;
    private int bulls;
    private int cows;
    private Long mastermaindId;
}
