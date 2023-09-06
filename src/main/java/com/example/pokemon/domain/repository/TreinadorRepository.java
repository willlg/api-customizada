package com.example.pokemon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokemon.domain.model.Treinador;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
    Optional<Treinador> findByEmail (String email);
}
