package br.com.caelum.comidinhas.tipoCozinha;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= Replace.NONE)
class TipoCozinhaRepositoryTest {
    @Autowired
    private TipoCozinhaRepository tipoCozinhaRepository;

    @Test
    void deve_retornar_verdadeiro_quando_nos_temos_um_tipo_de_cozinha(){
        tipoCozinhaRepository.save(new TipoCozinha("Italiana"));
        assertTrue(tipoCozinhaRepository.existsByNome("Italiana"));
    }

    @Test
    void deve_retornar_falso_quando_nos_nao_temos_um_tipo_de_cozinha(){
        tipoCozinhaRepository.save(new TipoCozinha("Portuguesa"));
        assertFalse(tipoCozinhaRepository.existsByNome("Brasileira"));
    }

    @Test
    void deve_retornar_a_lista_em_ordem_alfabetica(){
        TipoCozinha italiana = tipoCozinhaRepository.save(new TipoCozinha("Italiana"));
        TipoCozinha portuguesa = tipoCozinhaRepository.save(new TipoCozinha("Portuguesa"));
        TipoCozinha arabe = tipoCozinhaRepository.save(new TipoCozinha("Arabe"));
        List<TipoCozinha> tipoDeCozinhas = tipoCozinhaRepository.findAllByOrderByNome();
        MatcherAssert.assertThat(tipoDeCozinhas, contains(arabe,italiana,portuguesa));

    }
}
