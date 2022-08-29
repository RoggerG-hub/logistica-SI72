package com.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Ciudad;
import com.example.entities.Producto;
import com.example.entities.Proveedor;
import com.example.service.AlmacenService;
import com.example.service.CategoriaService;
import com.example.service.ProductoService;
import com.example.service.ProveedorService;

@Controller
public class ProductoController {
	@Autowired
	ProductoService productoService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	AlmacenService almacenService;
	@GetMapping("/producto/nuevo")
	public String registrarProducto(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("proveedores", proveedorService.listaP());
		model.addAttribute("categorias", categoriaService.listarCategoria());
		model.addAttribute("almacenes", almacenService.listarA());

		return "producto/form";
	}
	@PostMapping("/producto/registrar")
	public String registrarProducto1(@Validated @ModelAttribute Producto producto, BindingResult result, Model model) {		
		int rpta;
		if(result.hasErrors()) {
			model.addAttribute("mensaje", "Ingrese los datos");
			model.addAttribute("proveedores", proveedorService.listaP());
			model.addAttribute("categorias", categoriaService.listarCategoria());
			model.addAttribute("almacenes", almacenService.listarA());
			return "producto/form";
		}
		rpta=productoService.registrarProducto(producto);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una producto registrado con el mismo SKU");
			model.addAttribute("categorias", categoriaService.listarCategoria());
			model.addAttribute("almacenes", almacenService.listarA());

		}else 
		{
			productoService.registrarProducto(producto);
			model.addAttribute("mensaje", "Se registro el producto");
			model.addAttribute("producto", new Producto());
			model.addAttribute("proveedores", proveedorService.listaP());
			model.addAttribute("categorias", categoriaService.listarCategoria());
			model.addAttribute("almacenes", almacenService.listarA());
		}
		return "producto/form";
	}
	@GetMapping("/producto/lista")
	public String listarProductos(Model model) {
		model.addAttribute("productos",productoService.listarProductos());
		return "producto/listaP";
	}
	@GetMapping("/producto/edit/{id}")
	public String editP(@PathVariable Long id, Model model) {
		Producto st = productoService.encontrarProducto(id);

		model.addAttribute("producto", st);
		model.addAttribute("categorias", categoriaService.listarCategoria());
		model.addAttribute("almacenes", almacenService.listarA());
		return "producto/update";
	}
	@GetMapping("/producto/delete/{id}")
	public String deleteProducto( Model model,@PathVariable Long id) {
		try {
			productoService.eliminarProducto(id);

		} catch (Exception e) {
			model.addAttribute("mensaje", "El producto no se puede eliminar");

		}
		model.addAttribute("productos",productoService.listarProductos());
		return "producto/listaP";
	}
	@PostMapping("/producto/actualizar/{id}")
	public String updateLibro(@PathVariable Long id, @ModelAttribute("producto") Producto producto, Model model) {
		Producto st = productoService.encontrarProducto(id);

		st.setId(id);
		st.setNombre(producto.getNombre());
		st.setDescripcion(producto.getDescripcion());
		st.setSku(producto.getSku());
		st.setStock(producto.getStock());
		st.setUnidad(producto.getUnidad());
		st.setAlmacen(producto.getAlmacen());
		st.setCategoria(producto.getCategoria());
		st.setFechaB(st.getFechaB());
		st.setFechaR(st.getFechaR());
		
		productoService.actualizar(st);
	
			model.addAttribute("mensaje", "Se actualizo los datos del producto");
			model.addAttribute("producto", new Producto());
		

		return "redirect:/producto/lista";

	}
}