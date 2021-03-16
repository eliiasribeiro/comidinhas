package br.com.caelum.comidinha.tipoComida;

import javax.validation.constraints.*;

class TipoComidaFormNovo {

    @NotEmpty
    @Size(max = 50,message = "{tipo.comida.nome.size}")
    private String nome;

    @Deprecated
    TipoComidaFormNovo(){}

    TipoComidaFormNovo(@NotEmpty @Size(max = 50) String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    TipoComida toModel() {
        return new TipoComida(this.getNome());
    }
}
