package br.com.caelum.comidinha.tipoComida;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class TipoComida {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    @Size(max = 50)
    private String nome;


    @Deprecated
    public TipoComida() {
    }

    public TipoComida(Long id, @NotEmpty @Size(max = 50) String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoComida(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public TipoComida convertFormToEntity(Long id,TipoComidaForm form){
        return new TipoComida(id,form.getNome());
    }
}
