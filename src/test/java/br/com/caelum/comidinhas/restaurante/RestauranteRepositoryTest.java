package br.com.caelum.comidinhas.restaurante;

import br.com.caelum.comidinhas.tipoCozinha.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class RestauranteRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private TipoCozinhaRepository tipoCozinhaRepository;

    @Test
    void deve_retornar_um_possivel_restaurante_quando_tem_um_slug(){
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

        Optional<RestauranteCardapioOutput> possibleRestaurante = restauranteRepository.findBySlug("italiano");
        assertThat(possibleRestaurante.get().getNome()).isEqualTo(italiano.getNome());
        assertThat(possibleRestaurante.get().getDescricao()).isEqualTo(italiano.getDescricao());
        assertThat(possibleRestaurante.get().getLogo()).isEqualTo(italiano.getLogo());
        assertThat(possibleRestaurante.get().getTipoCozinha().getNome()).isEqualTo(tipoCozinhaItaliana.getNome());
        assertThat(possibleRestaurante.get().getSlug()).isEqualTo(italiano.getSlug());
    }

    @Test
    void deve_retornar_um_possivel_restaurante_vazio_quando_nao_existe_o_slug(){
        Optional<RestauranteCardapioOutput> possivelRestaurante = restauranteRepository.findBySlug("italiano");
        assertThat(possivelRestaurante).isEqualTo(Optional.empty());
    }



}
