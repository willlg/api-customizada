package com.example.pokemon.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.pokemon.domain.model.Treinador;
import com.example.pokemon.domain.repository.TreinadorRepository;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Treinador> optTreinador = treinadorRepository.findByEmail(username);
       if(optTreinador.isEmpty()){
        throw new UsernameNotFoundException("Usuário ou senha Inválidos.");
       }
       return optTreinador.get();
    }
    
}

