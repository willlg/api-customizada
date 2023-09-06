package com.example.pokemon.domain.exception;

public class ResourceBadRequestException extends RuntimeException{
    public ResourceBadRequestException(String mensagem){
        super(mensagem);
    }
    
}
