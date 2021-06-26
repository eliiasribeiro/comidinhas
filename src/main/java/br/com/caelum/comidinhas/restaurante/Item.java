package br.com.caelum.comidinhas.restaurante;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private List<Ingrediente> ingrediente;
}
