package br.com.caelum.comidinhas.restaurante;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal nota;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "avaliacao_id")
    private List<AvaliacaoComentarios> avaliacaoComentarios;
}
