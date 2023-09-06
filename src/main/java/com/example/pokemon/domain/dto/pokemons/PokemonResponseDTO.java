package com.example.pokemon.domain.dto.pokemons;

import java.util.Date;

import com.example.pokemon.domain.Enum.ETipoPokemon;

public class PokemonResponseDTO {
   private Long id;
    private String descricao;
    private ETipoPokemon tipo;
    private Double valor;
    private Date dataCadastro;
    private Date dataCaptura;
    private Date dataSoltura;
    private Date dataDano;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public ETipoPokemon getTipo() {
        return tipo;
    }
    public void setTipo(ETipoPokemon tipo) {
        this.tipo = tipo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getDataCaptura() {
        return dataCaptura;
    }
    public void setDataCaptura(Date dataCaptura) {
        this.dataCaptura = dataCaptura;
    }
    public Date getDataSoltura() {
        return dataSoltura;
    }
    public void setDataSoltura(Date dataSoltura) {
        this.dataSoltura = dataSoltura;
    }
    public Date getDataDano() {
        return dataDano;
    }
    public void setDataDano(Date dataDano) {
        this.dataDano = dataDano;
    }
}
