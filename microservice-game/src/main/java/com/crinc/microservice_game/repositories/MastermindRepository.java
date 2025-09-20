package com.crinc.microservice_game.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crinc.microservice_game.models.Mastermind;

@Repository
public interface MastermindRepository extends CrudRepository<Mastermind, Long> {
    
}
