package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Categoria;
import com.example.service.CategoriaService;


@Controller
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	private Categoria finCategoria;
	@GetMapping("/categoria/nuevo")
	public String registrarCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categoria/form";
	}
	@PostMapping("/categoria/registrar")
	public String registrarCategoria1(@Validated @ModelAttribute Categoria categoria, BindingResult result, Model model) {		
	
		LocalDate localDate = LocalDate.now();
		categoria.setFechaC(java.sql.Date.valueOf(localDate));
		categoria.setFechaB(java.sql.Date.valueOf(localDate));
		categoria.setFechaM(java.sql.Date.valueOf(localDate));
		int rpta;

		if(result.hasErrors()) {
			return "categoria/form";
		}
		rpta=categoriaService.registrarCategoria(categoria);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una categoria con ese nombre");

		}else 
		{
			categoriaService.registrarCategoria(categoria);
			model.addAttribute("mensaje", "Se registro nuevo categoria");
			model.addAttribute("categoria", new Categoria());
		}
			
		return "categoria/form";
	}
	@GetMapping("/categoria/lista")
	public String listarCategorias(Model model) {
		model.addAttribute("categorias",categoriaService.activo());
		return "categoria/listaC";
	}
	@GetMapping("/categoria/lista/baja")
	public String listarCategoriasS(Model model) {
		model.addAttribute("categorias",categoriaService.desactivo());
		return "categoria/listaA";
	}
	@GetMapping("/categoria/edit/{id}")
	public String editC(@PathVariable Long id, Model model) {
		Categoria st = categoriaService.encontrarCategoria(id);
		finCategoria=st;
		model.addAttribute("categoria", st);

		return "categoria/update";
	}
	@GetMapping("/categoria/delete/{id}")
	public String deleteCategoria(Model model,@PathVariable Long id) {
		try {
			categoriaService.deleteCategoriaById(id);

		} catch (Exception e) {
			model.addAttribute("mensaje", "La categoria no se puede eliminar");
		}
		return "redirect:/categoria/lista";
	}
	@PostMapping("/actualizar/categoria/{id}")
	public String updateLibro(@PathVariable Long id, @ModelAttribute("categoria") Categoria categoria, Model model) {
		Categoria st = categoriaService.encontrarCategoria(id);

		st.setId(id);
		st.setDescripcion(categoria.getDescripcion());
		st.setFechaC(categoria.getFechaC());
		st.setFechaB(categoria.getFechaB());
		st.setFechaM(categoria.getFechaM());
		int rpta;
		rpta=categoriaService.registrarCategoria(st);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una categoria con esa descripcion");

		}else 
		{
			categoriaService.registrarCategoria(st);
			model.addAttribute("mensaje", "Se actualizo los datos de la categoria");
			model.addAttribute("categoria", new Categoria());
		}

		return "redirect:/categoria/lista";

	}
	@PostMapping("/actualizar/probar/{id}")
	public String updateCC(@PathVariable Long id, @ModelAttribute("categoria") Categoria categoria, Model model) {
		
			Categoria st = categoriaService.encontrarCategoria(id);
			LocalDate localDate = LocalDate.now();

			st.setId(id);
			st.setDescripcion(categoria.getDescripcion());
			st.setFechaC(finCategoria.getFechaC());
			st.setFechaB(finCategoria.getFechaB());
			st.setFechaM(java.sql.Date.valueOf(localDate));
			st.setEstado(finCategoria.getEstado());
			categoriaService.actualizar(st);
		


		return "redirect:/categoria/lista";

	}
	@GetMapping("/categoria/baja/{id}")
	public String baja(Model model,@PathVariable Long id) {
		try {
			categoriaService.dar_baja(id);
			model.addAttribute("mensaje", "Se dio de baja la categoria con exito");

		} catch (Exception e) {
			model.addAttribute("mensaje", "No fue posible dar de baja la categoria");

		}
		model.addAttribute("categorias",categoriaService.activo());

		return "categoria/listaC";
	}
	@GetMapping("/categoria/activar/{id}")
	public String activo(Model model,@PathVariable Long id) {
		try {
			categoriaService.activar(id);
			model.addAttribute("mensaje", "Se activo la categoria con exito");

		} catch (Exception e) {
			model.addAttribute("mensaje", "No fue posible activar la categoria");

		}
		model.addAttribute("categorias",categoriaService.desactivo());

		return "categoria/listaA";
	}
}
