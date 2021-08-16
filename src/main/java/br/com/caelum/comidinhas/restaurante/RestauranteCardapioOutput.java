package br.com.caelum.comidinhas.restaurante;

interface RestauranteCardapioOutput {

 Long getId();
 String getNome();
 String getSlug();
 TipoCozinha getTipoCozinha();
 String getLogo();
 String getDescricao();


 interface TipoCozinha {
  String getNome();
 }
}
