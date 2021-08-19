package br.com.caelum.comidinhas.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RestauranteRepository extends JpaRepository<Restaurante,Long> {

    Optional<RestauranteCardapioOutput> findBySlug(String slug);

}
