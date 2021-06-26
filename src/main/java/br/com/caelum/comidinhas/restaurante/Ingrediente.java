package br.com.caelum.comidinhas.restaurante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy =  IDENTITY)
    private Long id;
    private String nome;
}
