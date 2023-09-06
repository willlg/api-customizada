package com.example.pokemon.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.pokemon.domain.dto.pokemons.PokemonRequestDTO;
import com.example.pokemon.domain.dto.pokemons.PokemonResponseDTO;
import com.example.pokemon.domain.exception.ResourceBadRequestException;
import com.example.pokemon.domain.exception.ResourceNotFoundException;
import com.example.pokemon.domain.model.Pokemon;
import com.example.pokemon.domain.model.Treinador;
import com.example.pokemon.domain.repository.PokemonRepository;

@Service
public class PokemonService implements ICRUDService<PokemonRequestDTO, PokemonResponseDTO> {
     @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PokemonResponseDTO> obterTodos() {
        Treinador treinador = (Treinador) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Pokemon> pokemons = pokemonRepository.findByTreinador(treinador);
        return pokemons.stream().map(pokemon -> mapper.map(pokemon, PokemonResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PokemonResponseDTO obterPorId(Long id) {
        Optional<Pokemon> optPokemon = pokemonRepository.findById(id);
        if(optPokemon.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o título com o ID:" + id);
        }
        return mapper.map(optPokemon.get(), PokemonResponseDTO.class);
    }

    @Override
    public PokemonResponseDTO cadastrar(PokemonRequestDTO dto) {
        validarPokemon(dto);
        Pokemon pokemon = mapper.map(dto, Pokemon.class);
        Treinador treinador = (Treinador) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pokemon.setTreinador(treinador);
        pokemon.setId(null);
        pokemon.setDataCadastro(new Date());
        pokemon = pokemonRepository.save(pokemon);
        return mapper.map(pokemon, PokemonResponseDTO.class);
    }

    @Override
    public PokemonResponseDTO atualizar(Long id, PokemonRequestDTO dto) {
        obterPorId(id);
        validarPokemon(dto);
        Pokemon pokemon = mapper.map(dto, Pokemon.class);
        Treinador treinador = (Treinador) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pokemon.setTreinador(treinador);
        pokemon.setId(id);
        pokemon = pokemonRepository.save(pokemon);
        return mapper.map(pokemon, PokemonResponseDTO.class);

    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        pokemonRepository.deleteById(id);
    }

    private void validarPokemon(PokemonRequestDTO dto){
        if(dto.getTipo() == null || dto.getDataSoltura() == null || dto.getValor() == null || dto.getDescricao() == null){
            throw new ResourceBadRequestException("Pokemon inválido - Campos Obrigatórios!");
        }
    }
    
    public List<PokemonResponseDTO> obterPorDataSoltura(String periodoInicial, String periodoFinal){
        List<Pokemon> pokemons = pokemonRepository.obterFluxoDeHPPorDataSoltura(periodoInicial, periodoFinal);
        return pokemons.stream()
        .map(pokemon -> mapper.map(pokemon, PokemonResponseDTO.class))
        .collect(Collectors.toList());
    }
}
