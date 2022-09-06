package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Almacen;
import com.example.repository.AlmacenRepository;

@Service
public class AlmacenService {
	@Autowired
	AlmacenRepository almacenRepository;

	public List<Almacen> listarA() {
		return almacenRepository.findAll();
	}

	public String registrarA(Almacen a) {
		almacenRepository.save(a);
		return a.getCodigo();
	}
	public void deleteA(Long a) 
	{
		almacenRepository.deleteById(a);
	}
	public Almacen encontrarAlmacen(Long id) 
	{
		return almacenRepository.findById(id).get();
	}
	public Almacen actualizarAlmacen(Almacen a) 
	{
		return almacenRepository.save(a);
	}
	public void dar_baja(Long id) 
	{
		LocalDate localDate = LocalDate.now();
		Almacen n = almacenRepository.findById(id).get();
		n.setEstado(0);
		n.setFechaB(java.sql.Date.valueOf(localDate));
		almacenRepository.save(n);
	}
}
