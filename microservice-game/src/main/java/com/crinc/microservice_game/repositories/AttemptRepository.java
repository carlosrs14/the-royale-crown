package com.crinc.microservice_game.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crinc.microservice_game.models.Attempt;

@Repository
public interface AttemptRepository extends CrudRepository<Attempt, Long> {
    List<Attempt> findByMastermaindId(Long mastermaindId);
}
