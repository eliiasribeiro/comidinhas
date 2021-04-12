package br.com.caelum.comidinhas.tipoCozinha;

import br.com.caelum.comidinhas.exception.RelationshipNotFoundException;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;


class TipoCozinhaInputEditar {

    @NotNull(message = "{tipo.cozinha.id.not.null}")
    private Long id;

    @NotEmpty
    @Size(max = 50,message = "{tipo.cozinha.nome.size}")
    private String nome;

    @Deprecated
    TipoCozinhaInputEditar(){}

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public TipoCozinhaInputEditar(@NotNull(message = "{tipo.cozinha.id.not.null}") Long id, @NotEmpty @Size(max = 50, message = "{tipo.cozinha.nome.size}") String nome) {
        this.id = id;
        this.nome = nome;
    }

    ///MUDAR EXCESSAO

    TipoCozinha toModel(Function<Long, Optional<TipoCozinha>> possivelTipoCozinha) {
        Assert.notNull(possivelTipoCozinha, "Função não deveria ser nula");
        var tipoCozinha = possivelTipoCozinha
                .apply(id)
                .orElseThrow(
                        () -> new RelationshipNotFoundException(format("O tipo de cozinha %s informado nao existe", nome)));

        return new TipoCozinha(tipoCozinha.getId(),nome);
    }
}
