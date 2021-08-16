package br.com.caelum.comidinhas.restaurante;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

interface ItemDoCardapioRepository extends JpaRepository<ItemDoCardapio,Long> {

    @Query(value = "SELECT " +
            "       ic.id as id, " +
            "       ic.nome as nome, " +
            "       ic.descricao as descricao, " +
            "       ic.preco as preco, " +
            "       ic.logo as logo, " +
            "       ic.cardapio_id as cardapioId " +
            "from item_cardapio ic " +
            "JOIN cardapio c on ic.cardapio_id = c.id " +
            "JOIN restaurante r on c.restaurante_id = r.id " +
            "WHERE r.slug = :slug",nativeQuery = true)
    List<ItemDoCardapioMenu> getByRestaurante(@Param("slug") String slug);
}
