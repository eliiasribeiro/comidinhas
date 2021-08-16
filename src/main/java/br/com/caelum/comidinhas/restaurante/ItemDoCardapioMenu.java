package br.com.caelum.comidinhas.restaurante;

import java.math.BigDecimal;

interface ItemDoCardapioMenu {

    Long getId();
    String getNome();
    String getDescricao();
    BigDecimal getPreco();
    String getLogo();
    Long getCardapioId();
}
