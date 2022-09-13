package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.OrdenCompraDetalle;
import com.example.entities.Producto;
import com.example.repository.OrdenCompraDetalleRepository;

@Service
public class OrdenCompraDetalleService {
	@Autowired
	OrdenCompraDetalleRepository ordenCompraDetalleRepository;
	@Autowired
	ProductoService productoService;
	public List<OrdenCompraDetalle> encontrarOrdenCompraDetalle(Long id) 
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
	public void suma(List<OrdenCompraDetalle> lista) 
	{
		for(int i=0;i<lista.size();i++) 
		{
			Producto actualizar = productoService.encontrarProducto(lista.get(i).getProducto().getId());
			int cantidad = actualizar.getStock()+lista.get(i).getCantidad();
			actualizar.setStock(cantidad);
			actualizar.setId(actualizar.getId());
			actualizar.setDescripcion(actualizar.getDescripcion());
			actualizar.setSku(actualizar.getSku());
			actualizar.setUnidad(actualizar.getUnidad());
			actualizar.setAlmacen(actualizar.getAlmacen());
			actualizar.setCategoria(actualizar.getCategoria());
			actualizar.setFechaB(actualizar.getFechaB());
			actualizar.setFechaR(actualizar.getFechaR());
			productoService.actualizar(actualizar);
		}
	}

}
