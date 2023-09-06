package com.example.pokemon.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.domain.Enum.ETipoPokemon;
import com.example.pokemon.domain.dto.dashboard.DashboardResponseDTO;
import com.example.pokemon.domain.dto.pokemons.PokemonResponseDTO;

@Service
public class DashboardService {
    @Autowired
    private PokemonService pokemonService;

    public DashboardResponseDTO obterFluxoDeHP(String periodoInicial, String periodoFinal){
        List<PokemonResponseDTO> pokemons = pokemonService.obterPorDataSoltura(periodoInicial, periodoFinal);
        Double totalDar = 0.0;
        Double totalDefender = 0.0;
        List<PokemonResponseDTO> pokemonsDar = new ArrayList<>();
        List<PokemonResponseDTO> pokemonsDefender = new ArrayList<>();
        Double saldo = 0.0;
        for(PokemonResponseDTO pokemon : pokemons){
            if(pokemon.getTipo() == ETipoPokemon.DADANO){
                totalDar += pokemon.getValor();
                pokemonsDar.add(pokemon);
            }else{
                totalDefender += pokemon.getValor();
                pokemonsDefender.add(pokemon);
            }
        }
        saldo = totalDefender - totalDar;
        return new DashboardResponseDTO(totalDar, totalDefender, saldo, pokemonsDar, pokemonsDefender);
    }
}
