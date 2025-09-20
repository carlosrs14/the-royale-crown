package com.crinc.microservice_game.exceptions;

public class NotPlayableException extends ApiException {
    public NotPlayableException(String status) {
        super("Mastermind status is " + status + ", not playable");
    }
}
