package br.com.caelum.comidinhas.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;

interface RestauranteRepository extends JpaRepository<Restaurante,Long> {
}
