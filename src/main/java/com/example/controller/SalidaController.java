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
import com.example.entities.OrdenCompraDetalle;
import com.example.entities.Pedido;
import com.example.entities.PedidoDetalle;
import com.example.entities.Producto;
import com.example.entities.SalidaMercaderia;
import com.example.entities.Usuario;
import com.example.service.PedidoDetalleService;
import com.example.service.PedidoService;
import com.example.service.ProductoService;
import com.example.service.SalidaMercaderiaService;
import com.example.service.UsuarioService;

@Controller
public class SalidaController {
	@Autowired
	PedidoService pedidoService;
	@Autowired
	PedidoDetalleService pedidoDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	Usuario usuario = new Usuario();
	@Autowired
	SalidaMercaderiaService salidaMercaderiaService;
	@GetMapping("/salida/pedido/{id}")
	public String nuevo(@PathVariable Long id,Model model){
		model.addAttribute("pedido",new Pedido());
		model.addAttribute("detalle",new PedidoDetalle());
		usuario = usuarioService.encontrarUsuario(id);
		return "salida/buscador";
	}
	@PostMapping("/pedido/buscar")
	public String buscarPedido(Model model, @ModelAttribute Pedido pedido) {
		if(pedidoService.validarCantidad(pedido.getCorrelativo())>0) 
		{
			model.addAttribute("pedido",pedidoService.buscarPedidoCorrelativo(pedido.getCorrelativo()));
			Pedido nuevo = pedidoService.buscarPedidoCorrelativo(pedido.getCorrelativo());
			model.addAttribute("detalle",pedidoDetalleService.encontrarPedidoDetalle(nuevo.getId()));
			if(pedidoDetalleService.encontrarPedidoDetalle(nuevo.getId()).getConfirmar()==0) 
			{
				if(restar(pedidoDetalleService.encontrarPedidoDetalle(nuevo.getId()))==1)
				{
					cambiar(pedidoDetalleService.encontrarPedidoDetalle(nuevo.getId()));
					/*Guardar Ingreso*/
					LocalDate localDate = LocalDate.now();
					SalidaMercaderia salidaMercaderia = new SalidaMercaderia();
					salidaMercaderia.setFechaR(java.sql.Date.valueOf(localDate));
					salidaMercaderia.setPedido(nuevo);
					salidaMercaderia.setUsuario(usuario);
					salidaMercaderiaService.registrar(salidaMercaderia);
				}else 
				{
					model.addAttribute("mensaje", "No se cuenta con la cantidad de stock disponible");

				}
			}
		}else 
		{
			model.addAttribute("pedido",new Pedido());
			model.addAttribute("detalle",new PedidoDetalle());
			model.addAttribute("mensaje", "La orden de compra no existe");
			return "salida/buscador";

		}

	


		return "salida/buscador";
	}
	private int restar(PedidoDetalle pedidoDetalle) 
	{
		Producto nuevo = productoService.encontrarProducto(pedidoDetalle.getProducto().getId());
		int cantidad = nuevo.getStock()-pedidoDetalle.getCantidad();
		if(cantidad>=0) 
		{
			nuevo.setStock(cantidad);
			nuevo.setId(nuevo.getId());
			nuevo.setAlmacen(nuevo.getAlmacen());
			nuevo.setCategoria(nuevo.getCategoria());
			nuevo.setDescripcion(nuevo.getDescripcion());
			nuevo.setFechaB(nuevo.getFechaB());
			nuevo.setFechaR(nuevo.getFechaR());
			nuevo.setNombre(nuevo.getNombre());
			nuevo.setSku(nuevo.getSku());
			nuevo.setUnidad(nuevo.getUnidad());
			productoService.actualizar(nuevo);
			return 1;

		}else 
		{
			return 0;
		}
	}
	private void cambiar(PedidoDetalle detalle) 
	{
		PedidoDetalle nuevo = new PedidoDetalle();
		nuevo.setCantidad(detalle.getCantidad());
		nuevo.setConfirmar(1);
		nuevo.setId(detalle.getId());
		nuevo.setPrecio(detalle.getPrecio());
		nuevo.setProducto(detalle.getProducto());
		nuevo.setPedido(detalle.getPedido());
		pedidoDetalleService.actualizar(nuevo);
	}
}
