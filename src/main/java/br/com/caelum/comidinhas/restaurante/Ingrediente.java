package br.com.caelum.comidinhas.restaurante;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Ingrediente implements Serializable {
    @Id
    @GeneratedValue(strategy =  IDENTITY)
    private Long id;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private HashSet<String> nome;

}
