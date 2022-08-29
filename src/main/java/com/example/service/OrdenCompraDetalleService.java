package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.OrdenCompraDetalle;
import com.example.repository.OrdenCompraDetalleRepository;

@Service
public class OrdenCompraDetalleService {
	@Autowired
	OrdenCompraDetalleRepository ordenCompraDetalleRepository;
	public OrdenCompraDetalle encontrarOrdenCompraDetalle(Long id) 
	{
		return ordenCompraDetalleRepository.findByOrden(id);
	}
	public OrdenCompraDetalle encontrarOrdenCompraDetalleS(Long id) 
	{
		return ordenCompraDetalleRepository.findById(id).get();
	}
	public OrdenCompraDetalle actualizar(OrdenCompraDetalle c) 
	{
		return ordenCompraDetalleRepository.save(c);
	}

}
