package com.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import com.example.entities.Ciudad;
import com.example.entities.Producto;
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
		model.addAttribute("categorias",categoriaService.listarCategoria());
		return "categoria/listaC";
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
		st.setNombre(categoria.getNombre());
		st.setDescripcion(categoria.getDescripcion());
		st.setFechaC(categoria.getFechaC());
		st.setFechaB(categoria.getFechaB());
		int rpta;
		rpta=categoriaService.registrarCategoria(st);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una categoria con ese nombre");

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

			st.setId(id);
			st.setNombre(categoria.getNombre());
			st.setDescripcion(categoria.getDescripcion());
			st.setFechaC(finCategoria.getFechaC());
			st.setFechaB(finCategoria.getFechaB());
			categoriaService.actualizar(st);
		


		return "redirect:/categoria/lista";

	}
}
