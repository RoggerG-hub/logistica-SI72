package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entities.OrdenCompra;
import com.example.service.OrdenCompraService;
import com.example.service.ProveedorService;

@Controller
public class OrdenCompraController {
	@Autowired
	OrdenCompraService ordenCompraService;

	@Autowired
	ProveedorService proveedorService;
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
}
