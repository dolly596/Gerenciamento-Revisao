package com.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoapi.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}