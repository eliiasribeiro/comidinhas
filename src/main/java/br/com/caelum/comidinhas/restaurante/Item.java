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
    @Column(columnDefinition = "text")
    private String descricao;
}
