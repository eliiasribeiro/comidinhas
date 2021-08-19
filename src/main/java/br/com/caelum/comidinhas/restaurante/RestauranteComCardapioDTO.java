package br.com.caelum.comidinhas.restaurante;

import java.io.Serializable;
import java.util.List;

class RestauranteComCardapioDTO implements Serializable {

    private final Long idRestaurante;
    private final String nomeRestaurante;
    private final String slugRestaurante;
    private final String tipoCozinhaNome;
    private final String logo;
    private final String descricao;
    private final List<ItemDoCardapioMenu> itemDoCardapioMenu;

    public RestauranteComCardapioDTO(RestauranteCardapioOutput restauranteCardapioOutput, List<ItemDoCardapioMenu> itemDoCardapioMenu){
        this.idRestaurante = restauranteCardapioOutput.getId();
        this.nomeRestaurante = restauranteCardapioOutput.getNome();
        this.slugRestaurante = restauranteCardapioOutput.getSlug();
        this.tipoCozinhaNome = restauranteCardapioOutput.getTipoCozinha().getNome();
        this.logo = restauranteCardapioOutput.getLogo();
        this.descricao = restauranteCardapioOutput.getDescricao();
        this.itemDoCardapioMenu = itemDoCardapioMenu;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public String getSlugRestaurante() {
        return slugRestaurante;
    }

    public String getTipoCozinhaNome() {
        return tipoCozinhaNome;
    }

    public String getLogo() {
        return logo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<ItemDoCardapioMenu> getItemDoCardapioMenu() {
        return itemDoCardapioMenu;
    }
}
