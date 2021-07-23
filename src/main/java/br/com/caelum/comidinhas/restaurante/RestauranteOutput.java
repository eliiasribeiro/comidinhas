package br.com.caelum.comidinhas.restaurante;

import javax.validation.constraints.NotNull;

class RestauranteOutput {
    private final Long id;
    private final String nome;
    private final String descricao;
    @NotNull
    private final Integer distancia;
    private final String tipoDeCozinhaNome;


    RestauranteOutput(Restaurante restaurante, Integer distancia){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.descricao = restaurante.getDescricao();
        this.distancia = distancia;
        this.tipoDeCozinhaNome = restaurante.getTipoCozinhaNome();
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

    public String getDescricao() {
        return descricao;
    }

    public Integer getDistancia() {
        return distancia;
    }
}
