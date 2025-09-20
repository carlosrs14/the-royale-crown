package com.crinc.microservice_game.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptRequestDTO {
    private String guess;
    private Long mastermaindId;
}
