package com.example.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokemon.domain.dto.pokemons.PokemonRequestDTO;
import com.example.pokemon.domain.dto.pokemons.PokemonResponseDTO;
import com.example.pokemon.domain.service.PokemonService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonResponseDTO>> obterTodos(){
        return ResponseEntity.ok(pokemonService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO>obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> cadastrar(@RequestBody PokemonRequestDTO dto){
        PokemonResponseDTO responseDTO = pokemonService.cadastrar(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @RequestBody PokemonRequestDTO dto){
        PokemonResponseDTO responseDTO = pokemonService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        pokemonService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

