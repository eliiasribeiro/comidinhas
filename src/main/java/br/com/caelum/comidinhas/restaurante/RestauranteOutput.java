package br.com.caelum.comidinhas.restaurante;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

class RestauranteOutput {
    private final Long id;
    private final String nome;
    @NotNull
    private final Integer distancia;
    private final String tipoDeCozinhaNome;
    private final String logo;
    private final String descricao;


    RestauranteOutput(Restaurante restaurante, Integer distancia){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.distancia = distancia;
        this.tipoDeCozinhaNome = restaurante.getTipoCozinhaNome();
        this.descricao = restaurante.getDescricao();
        this.logo = restaurante.getLogo();
    }

    public String getTipoDeCozinhaNome() {
        return tipoDeCozinhaNome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLogo() {
        return logo;
    }
}
