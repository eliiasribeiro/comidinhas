package br.com.caelum.comidinha.tipoComida;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
class TipoComida {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    @Size(max = 50)
    private String nome;


    @Deprecated
    public TipoComida() {
    }

    TipoComida(Long id, @NotEmpty @Size(max = 50) String nome) {
        this.id = id;
        this.nome = nome;
    }

    TipoComida(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
