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

    Long getId() {
        return id;
    }

    String getNome() {
        return nome;
    }

    String getDescricao() {
        return descricao;
    }

    Integer getDistancia() {
        return distancia;
    }
}
