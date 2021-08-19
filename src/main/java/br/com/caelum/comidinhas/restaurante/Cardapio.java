package br.com.caelum.comidinhas.restaurante;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "text")
    private String descricao;
    @OneToMany(mappedBy="cardapio",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDoCardapio> itens;
    @OneToOne
    private Restaurante restaurante;

    public Cardapio(String nome, String descricao, Restaurante restaurante) {
        this.nome = nome;
        this.descricao = descricao;
        this.restaurante = restaurante;
    }

    @Deprecated
    public Cardapio() {
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

    public List<ItemDoCardapio> getItens() {
        return itens;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
}
