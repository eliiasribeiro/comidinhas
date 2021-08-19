package br.com.caelum.comidinhas.restaurante;

import br.com.caelum.comidinhas.tipoCozinha.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ItemDoCardapioRepositoryTest {

    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private ItemDoCardapioRepository itemDoCardapioRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private TipoCozinhaRepository tipoCozinhaRepository;


    @Test
    void deve_retornar_item_do_cardapio_quando_temos_um_restaurante_com_slug(){
    TipoCozinha tipoCozinhaItaliana = tipoCozinhaRepository.save(new TipoCozinha("Italiana"));

    Restaurante italiano = restauranteRepository.save(new Restaurante(
            "Italiano",
            "77293023000166",
            "Av Paulista",
            "08255000",
            "asserts/logo.png",
            "italiano",
            LocalDateTime.now(),
            tipoCozinhaItaliana));
        Cardapio cardapio = cardapioRepository.save(new Cardapio("Cardapio", "Venha conferir o que preparamos para voce", italiano));
        ItemDoCardapio tortaDeMaca = new ItemDoCardapio("Torta de maca", new BigDecimal(12.20), "Nossa torta preparada com o maior amor", "torta.png", cardapio);
        ItemDoCardapio tortaDeLimao = new ItemDoCardapio("Torta de limao", new BigDecimal(18.20), "Nossa torta preparada com o maior amor", "limao.png", cardapio);
        ItemDoCardapio tortaDePera = new ItemDoCardapio("Torta de pera", new BigDecimal(15.20), "Nossa torta preparada com o maior amor", "pera.png", cardapio);

        itemDoCardapioRepository.saveAll(List.of(tortaDeMaca,tortaDeLimao,tortaDePera));

        List<ItemDoCardapioMenu> itensDoCardapio = itemDoCardapioRepository.findByCardapio_Restaurante_Slug("italiano");
        assertThat(itensDoCardapio.size()).isEqualTo(3);
        assertThat(itensDoCardapio.get(0).getNome()).isEqualTo(tortaDeMaca.getNome());
        assertThat(itensDoCardapio.get(1).getNome()).isEqualTo(tortaDeLimao.getNome());
        assertThat(itensDoCardapio.get(2).getNome()).isEqualTo(tortaDePera.getNome());
    }

    @Test
    void deve_retornar_itens_do_restaurante_vazio_pq_nao_tem_slug(){
        List<ItemDoCardapioMenu> itensDoCardapio = itemDoCardapioRepository.findByCardapio_Restaurante_Slug("italiano");
        assertThat(itensDoCardapio.size()).isEqualTo(0);
    }
}
