package br.com.caelum.comidinha.tipoComida;

import javax.validation.constraints.*;

public class TipoComidaForm {
    @NotEmpty
    @Size(max = 50)
    private String nome;


    public TipoComidaForm(@NotEmpty @Size(max = 50) String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public TipoComida toModel() {
        return new TipoComida(this.getNome());
    }
}
