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
////Todo o item do pedido faz referencia ao do cardapio
//Pedido
//
//        List<ItemDoPedido> itemsPedidos;
//
//
//ItemPedido
//
//        ItemCardapio itemCardapio
//        BigDecimal preco;
//        Integer quantidade;
//        String observacao;
//
//|ItemCardapioPedido|
//id id_item preco_item pedido_id
//1   1          2.59     1
