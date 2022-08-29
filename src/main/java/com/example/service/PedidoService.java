package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Pedido;
import com.example.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	public Pedido buscarPedidoCorrelativo(String correlativo) 
	{
		return pedidoRepository.findByCorrelativoContainingIgnoreCase(correlativo);
	}
	public Pedido buscarPedidoId(Long id) 
	{
		return pedidoRepository.findById(id).get();
	}
	public int validarCantidad(String correlativo) 
	{
		return pedidoRepository.verificarExistencia(correlativo);
	}
}
