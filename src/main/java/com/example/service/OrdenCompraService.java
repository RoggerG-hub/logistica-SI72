package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.OrdenCompra;
import com.example.repository.OrdenCompraRepository;

@Service
public class OrdenCompraService {
	@Autowired
	private OrdenCompraRepository ordenCompraRepository;
	public List<OrdenCompra> listarOrdenes()
	{
		return ordenCompraRepository.findAll();
	}
	public Long registrarOrden(OrdenCompra o) 
	{
		ordenCompraRepository.save(o);
		return o.getId();
	}
	public void eliminarOrden(Long id) 
	{
		ordenCompraRepository.deleteById(id);
	}
	public OrdenCompra encontrarOrdenCompra(Long id) 
	{
		return ordenCompraRepository.findById(id).get();
	}
	public OrdenCompra buscarOrdenCompra(String correlativo) 
	{
		return ordenCompraRepository.findByCorrelativoContainingIgnoreCase(correlativo);
	}
	public int validarCantidad(String correlativo) 
	{
		return ordenCompraRepository.verificarExistencia(correlativo);
	}
}
