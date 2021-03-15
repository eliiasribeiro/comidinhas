package br.com.caelum.comidinha.tipoComida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class TipoComidaBuilder implements Serializable {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;

    public TipoComidaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TipoComidaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public static TipoComidaBuilder umTipoComida() {
        return new TipoComidaBuilder();
    }

    public TipoComida cria() {
        return new TipoComida(this.id,this.nome);
    }

    public String comoJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
