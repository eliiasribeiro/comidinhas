package br.com.caelum.comidinhas.restaurante;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

interface RestauranteRepository extends JpaRepository<Restaurante,Long> {

    @Query("SELECT " +
            " r.id as id, " +
            " r.nome as nome, " +
            " r.slug as slug, " +
            " r.logo as logo, " +
            " r.descricao as descricao," +
            " r.tipoCozinha.nome as tipoDeCozinhaNome " +
            " from Restaurante r where r.slug = :slug ")
    Optional<RestauranteCardapioOutput> getBySlug(@Param("slug") String slug);

}
