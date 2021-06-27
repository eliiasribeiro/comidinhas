package br.com.caelum.comidinhas.restaurante;

public enum Categoria {
    ENTRADA("Entrada"),
    BEBIDAS("Bebidas"),
    COMBO("Combo"),
    PORCOES("Porçōes"),
    PRATOS("Pratos"),
    LANCHES("Lanches"),
    SOBREMESA("Sobremesa");

    private String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

