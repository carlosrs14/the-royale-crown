package com.crinc.microservice_game.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.crinc.microservice_game.dtos.request.MastermindRequestDTO;
import com.crinc.microservice_game.dtos.response.MastermindResponseDTO;
import com.crinc.microservice_game.services.MastermindService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/games/masterminds")
public class MastermindController {
    
    private final MastermindService mastermaindService;

    MastermindController(MastermindService mastermaindService) {
        this.mastermaindService = mastermaindService;
    }
    
    @PostMapping("")
    public ResponseEntity<MastermindResponseDTO> create(@RequestBody MastermindRequestDTO mastermaindRequestDTO) {
         return new ResponseEntity<>(mastermaindService.create(mastermaindRequestDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MastermindResponseDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(mastermaindService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<MastermindResponseDTO>> findAll() {
        return ResponseEntity.ok(mastermaindService.findAll());
    }

    @PostMapping("/{id}/abandon")
    public ResponseEntity<MastermindResponseDTO> abandone(@PathVariable Long id) {
        return ResponseEntity.ok(mastermaindService.abandone(id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        mastermaindService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
