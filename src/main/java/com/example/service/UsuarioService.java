package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.Usuario;
import com.example.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private UsuarioRepository usuarioRepository;
	public UsuarioService(UsuarioRepository usuarioRepository) 
	{
		this.usuarioRepository = usuarioRepository;
	}
	public Usuario registrarUsuario(Usuario u) 
	{
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuarioRepository.save(u);
	}
	public boolean validar(String n) 
	{
		if(usuarioRepository.findByDni(n)!=null) 
		{
			return true;
		}else 
		{
			return false;

		}
	}
	public Usuario encontrarUsuario(Long id) 
	{
		return usuarioRepository.findById(id).get();
	}
}
