package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Almacen;
import com.example.service.AlmacenService;

@Controller
public class AlmacenController {
	@Autowired
	AlmacenService almacenService;
	private Almacen almacenFin;
	@GetMapping("/almacen/nuevo")
	public String registrarA(Model model) {
		model.addAttribute("almacen", new Almacen());
		return "almacen/form";
	}
	@PostMapping("/almacen/registrar")
	public String registrarA1(@Validated @ModelAttribute Almacen almacen, BindingResult result, Model model) {		
		
		if(result.hasErrors()) {
			return "almacen/form";
		}

			almacenService.registrarA(almacen);
			model.addAttribute("mensaje", "Se registro el almacen");
			model.addAttribute("almacen", new Almacen());
			
		return "almacen/form";
	}
	@GetMapping("/almacen/lista")
	public String listarA(Model model) {
		model.addAttribute("almacenes",almacenService.listarA());
		return "almacen/listaA";
	}
	@GetMapping("/almacen/delete/{id}")
	public String deleteA(Model model,@PathVariable Long id) {
		try {
			almacenService.deleteA(id);

		} catch (Exception e) {
			model.addAttribute("mensaje", "La categoria no se puede eliminar");

		}
		return "redirect:/almacen/lista";
	}
	@GetMapping("/almacen/edit/{id}")
	public String editA(@PathVariable Long id, Model model) {
		Almacen st = almacenService.encontrarAlmacen(id);
		almacenFin=st;
		model.addAttribute("almacen", st);

		return "almacen/update";
	}
	@PostMapping("/actualizar/almacen/{id}")
	public String updateAl(@PathVariable Long id, @ModelAttribute("almacen") Almacen almacen, Model model) {

		Almacen st = almacenService.encontrarAlmacen(id);

		st.setId(id);
		st.setCodigo(almacen.getCodigo());
		st.setDescripcion(almacen.getDescripcion());
		st.setDireccion(almacen.getDireccion());
		st.setEstado(almacen.getEstado());
		st.setFechaB(almacenFin.getFechaB());
		st.setFechaC(almacenFin.getFechaC());

		almacenService.actualizarAlmacen(st);

		return "redirect:/almacen/lista";

	}
}