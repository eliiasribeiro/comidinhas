package br.com.caelum.comidinhas.restaurante;

import javax.persistence.*;

@Entity
public class AvaliacaoComentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String comentario;
}
