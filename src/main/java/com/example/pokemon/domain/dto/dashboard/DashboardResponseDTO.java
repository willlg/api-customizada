package com.example.pokemon.domain.dto.dashboard;

import java.util.List;

import com.example.pokemon.domain.dto.pokemons.PokemonResponseDTO;

public class DashboardResponseDTO {
    private Double totalDar;
    private Double totalDefender;
    private Double saldo;
    private List<PokemonResponseDTO> pokemonsDar;
    private List<PokemonResponseDTO> pokemonsDefender;
    
    public DashboardResponseDTO(){}

    public DashboardResponseDTO(Double totalDar, Double totalDefender, Double saldo, List<PokemonResponseDTO> pokemonsDar, List<PokemonResponseDTO> pokemonsDefender) {
        this.totalDar = totalDar;
        this.totalDefender = totalDefender;
        this.saldo = saldo;
        this.pokemonsDar = pokemonsDar;
        this.pokemonsDefender = pokemonsDefender;
    }
    public Double getTotalDar() {
        return totalDar;
    }
    public void setTotalDar(Double totalDar) {
        this.totalDar = totalDar;
    }
    public Double getTotalDefender() {
        return totalDefender;
    }
    public void setTotalDefender(Double totalDefender) {
        this.totalDefender = totalDefender;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public List<PokemonResponseDTO> getPokemonsDar() {
        return pokemonsDar;
    }
    public void setPokemonsDar(List<PokemonResponseDTO> pokemonsDar) {
        this.pokemonsDar = pokemonsDar;
    }
    public List<PokemonResponseDTO> getPokemonsDefender() {
        return pokemonsDefender;
    }
    public void setPokemonsDefender(List<PokemonResponseDTO> pokemonsDefender) {
        this.pokemonsDefender = pokemonsDefender;
    }
}
