package com.crinc.microservice_game.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crinc.microservice_game.dtos.request.AttemptRequestDTO;
import com.crinc.microservice_game.dtos.response.AttemptResponseDTO;
import com.crinc.microservice_game.services.AttemptService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/games/attempts") // TODO Cambiar a "/api/v1/games/mastermind/{id}/attempts"
public class AttemptController {
    
    private final AttemptService attemptService;

    AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("")
    public ResponseEntity<AttemptResponseDTO> create(@Valid @RequestBody AttemptRequestDTO attemptRequestDTO) {
        return new ResponseEntity<>(attemptService.create(attemptRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttemptResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(attemptService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<AttemptResponseDTO>> findAll() {
        return ResponseEntity.ok(attemptService.findAll());
    }

    @GetMapping("/mastermind/{mastermindId}")
    public ResponseEntity<List<AttemptResponseDTO>> findByMastermindId(@PathVariable Long mastermindId) {
        return ResponseEntity.ok(attemptService.findByMastermaindId(mastermindId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attemptService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
