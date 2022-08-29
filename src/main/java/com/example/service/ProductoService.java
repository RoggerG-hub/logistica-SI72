package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Producto;
import com.example.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	public List<Producto> listarProductos()
	{
		return productoRepository.findAll();
	}
	public int registrarProducto(Producto p) 
	{
		int existe = productoRepository.verificarExistencia(p.getSku());
		if(existe==0) 
		{
			if(buscar(p.getSku()).size()==0) 
			{
				productoRepository.save(p);

			}else 
			{
				existe = 1;
			}
		}
		return existe;
	}
	public void eliminarProducto(Long id) 
	{
		productoRepository.deleteById(id);
	}
	public Producto encontrarProducto(Long c) 
	{
		return productoRepository.findById(c).get();
	}
	private List<Producto> buscar(String sku) 
	{
		return productoRepository.findBySkuContainingIgnoreCase(sku);
	}
	public Producto actualizar(Producto c) 
	{
		return productoRepository.save(c);
	}
}
