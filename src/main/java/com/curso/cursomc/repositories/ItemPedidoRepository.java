package com.curso.cursomc.repositories;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
