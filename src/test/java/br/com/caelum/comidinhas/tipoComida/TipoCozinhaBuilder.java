package br.com.caelum.comidinhas.tipoComida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class TipoCozinhaBuilder implements Serializable {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;

    public TipoCozinhaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TipoCozinhaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public static TipoCozinhaBuilder umTipoComida() {
        return new TipoCozinhaBuilder();
    }

    public TipoCozinha cria() {
        return new TipoCozinha(this.id,this.nome);
    }

    public String comoJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
