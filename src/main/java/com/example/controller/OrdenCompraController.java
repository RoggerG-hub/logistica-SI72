package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entities.OrdenCompra;
import com.example.service.ClienteService;
import com.example.service.OrdenCompraService;
import com.example.service.PedidoService;
import com.example.service.ProveedorService;

@Controller
public class OrdenCompraController {
	@Autowired
	OrdenCompraService ordenCompraService;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	ClienteService clienteService;
	@GetMapping("/orden/nuevo")
	public String registrarOrden(Model model) {
		model.addAttribute("ordenCompra", new OrdenCompra());
		model.addAttribute("proveedores",proveedorService.listaP());
		
		return "orden/form";
	}
	@GetMapping("/orden/lista")
	public String listarOrdenes(Model model) {
		model.addAttribute("ordenes",ordenCompraService.listarOrdenes());
		return "orden/listaO";
	}
	@GetMapping("/pedido/lista")
	public String listarPedidos(Model model) {
		model.addAttribute("pedidos",pedidoService.listarPedidos());
		return "pedido/listaP";
	}
	@GetMapping("/cliente/lista")
	public String listarC(Model model) {
		model.addAttribute("clientes",clienteService.listarClientes());
		return "cliente/listaC";
	}
}
