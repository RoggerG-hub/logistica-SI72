package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.Categoria;
import com.example.repository.CategoriaRepository;

@Service
public class CategoriaService {
	private CategoriaRepository categoriaRepository;
	public CategoriaService(CategoriaRepository categoriaRepository) 
	{
		this.categoriaRepository=categoriaRepository;
	}
	public List<Categoria> listarCategoria() 
	{
		return categoriaRepository.findAll();
	}
	public int registrarCategoria(Categoria c) 
	{
		int existe = categoriaRepository.verificarExistencia(c.getNombre());
		if(existe==0) 
		{
			if(buscar(c.getNombre()).size()==0) 
			{
				
				categoriaRepository.save(c);

			}else 
			{
				existe=1;
			}
		}
		return existe;
	}
	public void deleteCategoriaById(Long id) {
		categoriaRepository.deleteById(id);
	}
	private List<Categoria> buscar(String nombre) 
	{
		return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
	}
	public Categoria encontrarCategoria(Long c) 
	{
		return categoriaRepository.findById(c).get();
	}
	public Categoria actualizar(Categoria c) 
	{
		return categoriaRepository.save(c);
	}
	
}
