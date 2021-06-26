//package br.com.caelum.comidinhas.restaurante;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Cardapio {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String nome;
//    @Enumerated(value = EnumType.STRING)
//    private Categoria categoria;
//    private String descricao;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "item_id")
//    private List<Item> itens;
//}
