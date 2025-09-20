package com.crinc.microservice_game.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String resource, Object id) {
        super(resource + "not found with id " + id);
    }
    
}
