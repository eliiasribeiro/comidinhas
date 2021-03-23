package br.com.caelum.comidinhas.tipoComida;

import javax.validation.constraints.*;

class TipoCozinhaInputNovo {

    @NotEmpty
    @Size(max = 50,message = "{tipo.comida.nome.size}")
    private String nome;

    @Deprecated
    TipoCozinhaInputNovo(){}

    TipoCozinhaInputNovo(@NotEmpty @Size(max = 50) String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    TipoCozinha toModel() {
        return new TipoCozinha(this.getNome());
    }
}
