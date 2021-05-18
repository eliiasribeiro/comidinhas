package br.com.caelum.comidinhas.restaurante;

import java.util.Random;

class RestauranteOutput {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final Integer distancia;


    RestauranteOutput(Restaurante restaurante){
        var random = new Random();
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.descricao = restaurante.getDescricao();
        this.distancia = random.nextInt(100);
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
