package br.com.caelum.comidinha.tipoComida;

import javax.validation.constraints.*;

class TipoComidaFormNovo {

    @NotEmpty
    @Size(max = 50)
    private String nome;


    TipoComidaFormNovo(@NotEmpty @Size(max = 50) String nome) {
        this.nome = nome;
    }

    String getNome() {
        return nome;
    }

    TipoComida toModel() {
        return new TipoComida(this.getNome());
    }
}
