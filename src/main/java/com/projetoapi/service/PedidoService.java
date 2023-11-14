package com.projetoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapi.entities.Pedido;
import com.projetoapi.repository.PedidoRepository;

@Service
public class PedidoService {
	private PedidoRepository pedidoRepository;

	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public List<Pedido> buscarTodosPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarId(Long id) {
		Optional<Pedido> Pedido = pedidoRepository.findById(id);
		return Pedido.orElse(null);
	}

	public Pedido salvarPedido(Pedido usuario) {
		return pedidoRepository.save(usuario);
	}

	public Pedido alterarPedido(Long id, Pedido alterarpedi) {
		Optional<Pedido> existe = pedidoRepository.findById(id);
		if (existe.isPresent()) {
			alterarpedi.setId(id);
			return pedidoRepository.save(alterarpedi);
		}
		return null;
	}

	public boolean apagarPedido(Long id) {
		Optional<Pedido> existe = pedidoRepository.findById(id);
		if (existe.isPresent()) {
			pedidoRepository.deleteById(id);
			return true;
		}

		return false;
	}

}