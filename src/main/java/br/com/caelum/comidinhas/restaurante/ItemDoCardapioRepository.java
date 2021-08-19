package br.com.caelum.comidinhas.restaurante;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

interface ItemDoCardapioRepository extends JpaRepository<ItemDoCardapio,Long> {

    List<ItemDoCardapioMenu> findByCardapio_Restaurante_Slug(@Param("slug") String slug);
}
