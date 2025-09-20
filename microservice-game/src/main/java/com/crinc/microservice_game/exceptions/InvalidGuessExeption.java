package com.crinc.microservice_game.exceptions;

public class InvalidGuessExeption extends ApiException {

    public InvalidGuessExeption(String message) {
        super(message);
    }
    
}
