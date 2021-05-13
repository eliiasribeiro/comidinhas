package br.com.caelum.comidinhas.restaurante;

import br.com.caelum.comidinhas.tipoCozinha.TipoCozinha;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
class Restaurante {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Size(max = 50)
    @NotNull
    @Column(unique = true)
    private String nome;
    @Size(max = 14)
    @NotNull
    @Column(unique = true)
    @CNPJ
    private String cnpj;
    @Size(max = 255)
    @NotNull
    private String endereco;
    @Size(max = 8)
    @NotNull
    private String cep;
    @Size(max = 255)
    @NotNull
    private String descricao;
    @ManyToOne
    private TipoCozinha tipoCozinha;

    @Deprecated
    Restaurante() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoCozinha getTipoCozinha() {
        return tipoCozinha;
    }
}
