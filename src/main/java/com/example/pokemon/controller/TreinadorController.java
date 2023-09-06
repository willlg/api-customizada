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

import com.example.pokemon.domain.dto.treinador.TreinadorRequestDTO;
import com.example.pokemon.domain.dto.treinador.TreinadorResponseDTO;
import com.example.pokemon.domain.service.TreinadorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/treinadores")
public class TreinadorController {
    @Autowired
    private TreinadorService treinadorService;

    @GetMapping
    public ResponseEntity<List<TreinadorResponseDTO>> 
    obterTodos(){
        return ResponseEntity.ok(treinadorService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinadorResponseDTO> 
    obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(treinadorService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<TreinadorResponseDTO>
    cadastrar(@RequestBody TreinadorRequestDTO dto){
        TreinadorResponseDTO treinador = treinadorService.cadastrar(dto);
        return new ResponseEntity<TreinadorResponseDTO>(treinador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinadorResponseDTO>
    atualizar(@PathVariable Long id, @RequestBody TreinadorRequestDTO dto){
        TreinadorResponseDTO treinador = treinadorService.atualizar(id, dto);
        return new ResponseEntity<TreinadorResponseDTO>(treinador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> 
    deletar(@PathVariable Long id){
        treinadorService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   
}

