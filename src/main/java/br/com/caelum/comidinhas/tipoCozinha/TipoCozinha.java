package br.com.caelum.comidinhas.tipoCozinha;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class TipoCozinha {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    @Size(max = 50, message = "{tipo.cozinha.nome.size}")
    private String nome;

    @Deprecated
    public TipoCozinha() {
    }

    public TipoCozinha(Long id, @NotEmpty @Size(max = 50) String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoCozinha(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

}
