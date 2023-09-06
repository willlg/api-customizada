package com.example.pokemon.domain.dto.treinador;

public class LoginResponseDTO {
    private String token;
    private TreinadorResponseDTO treinador;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public TreinadorResponseDTO getTreinador() {
        return treinador;
    }
    public void setTreinador(TreinadorResponseDTO treinador) {
        this.treinador = treinador;
    }
}
