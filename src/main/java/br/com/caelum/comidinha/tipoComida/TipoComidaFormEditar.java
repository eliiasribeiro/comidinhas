package br.com.caelum.comidinha.tipoComida;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

class TipoComidaFormEditar {

    @NotNull(message = "{tipo.comida.id.not.null}")
    private Long id;

    @NotEmpty
    @Size(max = 50,message = "{tipo.comida.nome.size}")
    private String nome;

    @Deprecated
    TipoComidaFormEditar(){}

    public String getNome() {
        return nome;
    }

    TipoComida toModel(TipoComidaRepository tipoDeCozinhaRepository) {
        TipoComida tipoDeCozinha = tipoDeCozinhaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tipoDeCozinha.setNome(nome);
        return tipoDeCozinha;
    }
}
