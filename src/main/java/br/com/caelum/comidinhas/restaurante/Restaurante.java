package br.com.caelum.comidinhas.restaurante;

import br.com.caelum.comidinhas.tipoCozinha.TipoCozinha;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Restaurante {

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
    private String descricao;
    @ManyToOne
    private TipoCozinha tipoCozinha;
    private BigDecimal taxaDeEntrega;
    @NotNull
    private String logo;
    @NotNull
    private String slug;
    @NotNull
    private LocalDateTime createdAt = now();

    Restaurante(Long id, String nome, String cnpj, String endereco, String cep, String descricao, TipoCozinha tipoCozinha) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cep = cep;
        this.descricao = descricao;
        this.tipoCozinha = tipoCozinha;
    }

    public Restaurante(String nome, String cnpj, String endereco, String cep, String logo, String slug, LocalDateTime createdAt, TipoCozinha tipoCozinha) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.cep = cep;
        this.logo = logo;
        this.slug = slug;
        this.createdAt = createdAt;
        this.tipoCozinha = tipoCozinha;
    }

    public Restaurante() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoCozinha getTipoCozinha() {
        return tipoCozinha;
    }

    public String getTipoCozinhaNome(){
        return tipoCozinha.getNome();
    }

    public String getLogo() {
        return logo;
    }

    public String getSlug() {
        return slug;
    }
}
