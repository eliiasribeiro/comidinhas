package br.com.caelum.comidinhas.restaurante;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Item_Cardapio")
public class ItemDoCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @Column(columnDefinition = "text")
    private String descricao;
    private String logo;
    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    @Deprecated
    public ItemDoCardapio() {
    }

    public ItemDoCardapio(String nome, BigDecimal preco, String descricao, String logo, Cardapio cardapio) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.logo = logo;
        this.cardapio = cardapio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLogo() {
        return logo;
    }
}
