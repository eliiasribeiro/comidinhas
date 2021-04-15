package br.com.caelum.comidinhas.tipoCozinha;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;


@DataJpaTest
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
        assertFalse(tipoCozinhaRepository.existsByNome("Brasileira"));
    }

    @Test
    void deve_retornar_a_lista_em_ordem_alfabetica(){
        TipoCozinha italiana = tipoCozinhaRepository.save(new TipoCozinha("Italiana"));
        TipoCozinha portuguesa = tipoCozinhaRepository.save(new TipoCozinha("Portuguesa"));
        TipoCozinha arabe = tipoCozinhaRepository.save(new TipoCozinha("Arabe"));
        List<TipoCozinha> tipoDeCozinhas = tipoCozinhaRepository.findAllByOrderByNome();
        assertThat(tipoDeCozinhas).contains(arabe,italiana,portuguesa);

    }
}
