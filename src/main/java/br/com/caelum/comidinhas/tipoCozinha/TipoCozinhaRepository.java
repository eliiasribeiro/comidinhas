package br.com.caelum.comidinhas.tipoCozinha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

interface TipoCozinhaRepository extends JpaRepository<TipoCozinha,Long> {
    List<TipoCozinha> findAllByOrderByNome();

    boolean existsByNome(String nome);

}
