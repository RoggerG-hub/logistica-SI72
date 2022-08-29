package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.PedidoDetalle;
import com.example.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService {
	@Autowired
	PedidoDetalleRepository pedidoDetalleRepository;
	public PedidoDetalle encontrarPedidoDetalle(Long id) 
	{
		return pedidoDetalleRepository.findByPedido(id);
	}
	public PedidoDetalle actualizar(PedidoDetalle c) 
	{
		return pedidoDetalleRepository.save(c);
	}
}
