package br.com.caelum.comidinhas.tipoComida;

import javax.validation.constraints.*;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Objects.isNull;


class TipoCozinhaInputEditar {

    @NotNull(message = "{tipo.comida.id.not.null}")
    private Long id;

    @NotEmpty
    @Size(max = 50,message = "{tipo.comida.nome.size}")
    private String nome;

    @Deprecated
    TipoCozinhaInputEditar(){}

    public String getNome() {
        return nome;
    }

    TipoCozinha toModel(Function<Long, Optional<TipoCozinha>> possivelTipoComida) {
        if(!isNull(possivelTipoComida)){
            var tipoComida = possivelTipoComida
                    .apply(id)
                    .orElseThrow(
                            () -> new IllegalArgumentException(format("O tipo de comida %s informado nao existe", id)));

            return new TipoCozinha(tipoComida.getId(),nome);
        }
        return new TipoCozinha(nome);
    }

    public Long getId() {
        return id;
    }
}
