package com.example.pokemon.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pokemon.domain.model.Pokemon;
import com.example.pokemon.domain.model.Treinador;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    
     @Query(nativeQuery = true, value = "SELECT * FROM public.pokemon " +
            "WHERE data_soltura " +
            "BETWEEN TO_TIMESTAMP(:periodoInicial, 'YYYY-MM-DD hh24:MI:SS') AND " +
            "TO_TIMESTAMP(:periodoFinal, 'YYYY-MM-DD hh24:MI:SS')")
    List<Pokemon> obterFluxoDeHPPorDataSoltura(
        @Param("periodoInicial") String periodoInicial,
        @Param("periodoFinal") String periodoFinal);

    List<Pokemon> findByTreinador(Treinador treinador);
}
