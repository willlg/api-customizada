package com.example.pokemon.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.pokemon.common.ConversorData;
import com.example.pokemon.domain.dto.treinador.LoginRequestDTO;
import com.example.pokemon.domain.dto.treinador.LoginResponseDTO;
import com.example.pokemon.domain.dto.treinador.TreinadorResponseDTO;
import com.example.pokemon.domain.model.ErroResposta;
import com.example.pokemon.domain.model.Treinador;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
   // @Autowired
   // private ModelMapper mapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try{
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Usuário ou senha Inválidos");
        }catch(Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        Treinador treinador = (Treinador) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);
       // Alteração - TreinadorResponseDTO treinadorResponse =  mapper.map(treinador, TreinadorResponseDTO.class);
        TreinadorResponseDTO treinadorResponse = new TreinadorResponseDTO();
        treinadorResponse.setID(treinador.getId());
        treinadorResponse.setNome(treinador.getNome());
        treinadorResponse.setRegiao(treinador.getRegiao());
        treinadorResponse.setDataCadastro(treinador.getDataCadastro());
        treinadorResponse.setDataInativacao(treinador.getDataInativacao());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("Bearer " + token);
        loginResponseDTO.setTreinador(treinadorResponse);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponseDTO));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
    AuthenticationException failed) throws IOException, ServletException{
        String dataHora = ConversorData.converterDataParaDataHora(new Date());
        ErroResposta erro =  new ErroResposta(dataHora, 401, "Unauthorized", failed.getMessage());
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(erro));
    }

}