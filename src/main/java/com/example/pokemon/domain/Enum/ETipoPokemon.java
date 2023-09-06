package com.example.pokemon.domain.Enum;

public enum ETipoPokemon {
    DEFENDEDANO("Defende dano"),
    DADANO("Da dano");
    private String valor;

    private ETipoPokemon(String valor){
        this.valor = valor;
    }

    public String getvalor() {
        return valor;
    }
}
