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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cardapio_id")
    private List<Item> itens;
    @OneToOne
    private Restaurante restaurante;
}
