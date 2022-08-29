package com.example.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.IngresoMercaderia;
import com.example.entities.OrdenCompra;
import com.example.entities.OrdenCompraDetalle;
import com.example.entities.Producto;
import com.example.entities.Usuario;
import com.example.service.IngresoMercaderiaService;
import com.example.service.OrdenCompraDetalleService;
import com.example.service.OrdenCompraService;
import com.example.service.ProductoService;
import com.example.service.UsuarioService;

@Controller
public class IngresoController {
	@Autowired
	OrdenCompraService ordenCompraService;
	@Autowired
	OrdenCompraDetalleService ordenCompraDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	IngresoMercaderiaService ingresoMercaderiaService;
	@Autowired
	UsuarioService usuarioService;
	Usuario usuario = new Usuario();
	@GetMapping("/ingreso/orden/{id}")
	public String nuevo(@PathVariable Long id,Model model){
		model.addAttribute("ordenCompra",new OrdenCompra());
		model.addAttribute("detalle",new OrdenCompraDetalle());
		usuario = usuarioService.encontrarUsuario(id);
		return "ingreso/buscador";
	}
	@PostMapping("/orden/buscar")
	public String buscarOrden(Model model, @ModelAttribute OrdenCompra ordenCompra) {
	
			if(ordenCompraService.validarCantidad(ordenCompra.getCorrelativo())>0) 
			{
				/*Encontrar Orden*/
				model.addAttribute("ordenCompra", ordenCompraService.buscarOrdenCompra(ordenCompra.getCorrelativo()));
				OrdenCompra orden = ordenCompraService.buscarOrdenCompra(ordenCompra.getCorrelativo());
				model.addAttribute("detalle",ordenCompraDetalleService.encontrarOrdenCompraDetalle(orden.getId()));
				if(ordenCompraDetalleService.encontrarOrdenCompraDetalle(orden.getId()).getConfirmar()==0) 
				{
					sumar(ordenCompraDetalleService.encontrarOrdenCompraDetalle(orden.getId()));
					cambiar(ordenCompraDetalleService.encontrarOrdenCompraDetalle(orden.getId()));
					/*Guardar Ingreso*/
					LocalDate localDate = LocalDate.now();
					IngresoMercaderia ingresoMercaderia = new IngresoMercaderia();
					ingresoMercaderia.setFechaR(java.sql.Date.valueOf(localDate));
					ingresoMercaderia.setOrdenCompra(orden);
					ingresoMercaderia.setUsuario(usuario);
					ingresoMercaderiaService.registrar(ingresoMercaderia);
				}

				
			}else 
			{
				model.addAttribute("ordenCompra",new OrdenCompra());
				model.addAttribute("detalle",new OrdenCompraDetalle());
				model.addAttribute("mensaje", "La orden de compra no existe");
				return "ingreso/buscador";
			}

		return "ingreso/buscador";
	}
	private void sumar(OrdenCompraDetalle detalle)
	{
		
		Producto actualizar = productoService.encontrarProducto(detalle.getProducto().getId());
		int cantidad = actualizar.getStock()+detalle.getCantidad();
		actualizar.setStock(cantidad);
		actualizar.setId(actualizar.getId());
		actualizar.setNombre(actualizar.getNombre());
		actualizar.setDescripcion(actualizar.getDescripcion());
		actualizar.setSku(actualizar.getSku());
		actualizar.setUnidad(actualizar.getUnidad());
		actualizar.setAlmacen(actualizar.getAlmacen());
		actualizar.setCategoria(actualizar.getCategoria());
		actualizar.setFechaB(actualizar.getFechaB());
		actualizar.setFechaR(actualizar.getFechaR());
		productoService.actualizar(actualizar);
	}
	private void cambiar(OrdenCompraDetalle detalle) 
	{
		OrdenCompraDetalle nuevo = new OrdenCompraDetalle();
		nuevo.setCantidad(detalle.getCantidad());
		nuevo.setConfirmar(1);
		nuevo.setCosto(detalle.getCosto());
		nuevo.setId(detalle.getId());
		nuevo.setOrdenCompra(detalle.getOrdenCompra());
		nuevo.setProducto(detalle.getProducto());
		ordenCompraDetalleService.actualizar(nuevo);
	}
}
