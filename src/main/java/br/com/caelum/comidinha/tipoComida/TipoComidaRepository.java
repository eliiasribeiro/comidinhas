package br.com.caelum.comidinha.tipoComida;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TipoComidaRepository extends JpaRepository<TipoComida,Long> {
    List<TipoComida> findAllByOrderByNome();

    boolean findByNome(String nome);

    boolean existsByNome(String nome);
}
