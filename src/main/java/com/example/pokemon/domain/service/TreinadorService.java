package com.example.pokemon.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pokemon.domain.dto.treinador.TreinadorRequestDTO;
import com.example.pokemon.domain.dto.treinador.TreinadorResponseDTO;
import com.example.pokemon.domain.exception.ResourceBadRequestException;
import com.example.pokemon.domain.exception.ResourceNotFoundException;
import com.example.pokemon.domain.model.Treinador;
import com.example.pokemon.domain.repository.TreinadorRepository;

@Service
public class TreinadorService implements ICRUDService <TreinadorRequestDTO, TreinadorResponseDTO> {
    @Autowired
    private TreinadorRepository treinadorRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<TreinadorResponseDTO> obterTodos() {
        List<Treinador> treinadors = treinadorRepository.findAll();
        return treinadors.stream().map(treinador -> mapper.map(treinador, TreinadorResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TreinadorResponseDTO obterPorId(Long id) {
        Optional<Treinador> optTreinador = treinadorRepository.findById(id);
        if(optTreinador.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível enocontrar o treinador com o id:" + id);
        }
        return mapper.map(optTreinador.get(), TreinadorResponseDTO.class);
    }

    @Override
    public TreinadorResponseDTO cadastrar(TreinadorRequestDTO dto) {
        if(dto.getEmail()==null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e senha são obrigatórios!");
        }
        Optional<Treinador> optTreinador = treinadorRepository.findByEmail(dto.getEmail());
        if(optTreinador.isPresent()){
            throw new ResourceBadRequestException("Já existe um treinador cadastrado com esse email!" + dto.getEmail());
        }
        Treinador treinador = mapper.map(dto, Treinador.class);
        treinador.setDataCadastro(new Date());
        String senha = passwordEncoder.encode(treinador.getSenha());
        treinador.setSenha(senha);
        treinador.setId(null);
        treinador = treinadorRepository.save(treinador);
        return mapper.map(treinador, TreinadorResponseDTO.class);

    }

    @Override
    public TreinadorResponseDTO atualizar(Long id, TreinadorRequestDTO dto) {
        
       TreinadorResponseDTO treinadorBanco = obterPorId(id);
        if(dto.getEmail()==null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e senha são obrigatórios!");
        }
        Treinador treinador = mapper.map(dto, Treinador.class);
        treinador.setId(id);
        treinador.setDataCadastro(treinadorBanco.getDataCadastro()); 
        treinador.setDataInativacao(treinadorBanco.getDataInativacao());
        treinador = treinadorRepository.save(treinador);
        return mapper.map(treinador, TreinadorResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        Optional<Treinador> optTreinador = treinadorRepository.findById(id);
        if(optTreinador.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível enocontrar o treinador com o id:" + id);
        }
        Treinador treinador = optTreinador.get();
        treinador.setDataInativacao(new Date());
        treinadorRepository.save(treinador);
    }
}
