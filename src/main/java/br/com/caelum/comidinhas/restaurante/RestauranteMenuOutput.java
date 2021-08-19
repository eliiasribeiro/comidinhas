package br.com.caelum.comidinhas.restaurante;

import javax.validation.constraints.NotNull;

class RestauranteMenuOutput {

    private final Long id;
    private final String nome;
    private final String slug;
    @NotNull
    private final Integer distancia;
    private final String tipoDeCozinhaNome;
    private final String logo;

    RestauranteMenuOutput(Restaurante restaurante, Integer distancia){
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.slug = restaurante.getSlug();
        this.distancia = distancia;
        this.tipoDeCozinhaNome = restaurante.getTipoCozinhaNome();
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

    public String getLogo() {
        return logo;
    }

    public String getSlug() {
        return slug;
    }
}
