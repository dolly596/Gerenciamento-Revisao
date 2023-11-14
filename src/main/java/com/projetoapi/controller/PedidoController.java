package com.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoapi.entities.Pedido;
import com.projetoapi.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	private final PedidoService pedidoService;

	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario por ID")
	public ResponseEntity<Pedido> buscarId(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscarId(id);
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os usuarios")
	public ResponseEntity<List<Pedido>> buscarTodosPedidos() {
		List<Pedido> pedido = pedidoService.buscarTodosPedidos();
		return ResponseEntity.ok(pedido);
	}

	@PostMapping
	@Operation(summary = "Inserindo Dados")
	public ResponseEntity<Pedido> salvarPedido(@RequestBody @Valid Pedido produto) {
		Pedido salvar = pedidoService.salvarPedido(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
	}

	@PutMapping
	@Operation(summary = "Aterando Dados")
	public ResponseEntity<Pedido> alterarPedido(@PathVariable Long id, @RequestBody @Valid Pedido produto) {
		Pedido alterar = pedidoService.alterarPedido(id, produto);
		if (alterar != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletando Dados")
	public ResponseEntity<Pedido> apagarPedido(@PathVariable Long id) {
		boolean apagar = pedidoService.apagarPedido(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}