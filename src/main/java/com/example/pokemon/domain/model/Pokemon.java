package com.example.pokemon.domain.model;

import java.util.Date;

import com.example.pokemon.domain.Enum.ETipoPokemon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPokemon")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "idTreinador")
    private Treinador treinador;
    private ETipoPokemon tipo;
    @Column(nullable = false)
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
    public Treinador getTreinador() {
        return treinador;
    }
    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
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
