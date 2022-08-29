package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Ingrese un nombre")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;

	@NotEmpty(message = "Ingrese una descripción")
	@Column(name = "descripcion", nullable = false, length = 70)
	private String descripcion;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaC;
	@FutureOrPresent(message = "Fecha de baja no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_baja", nullable = false)
	private Date fechaB;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaC() {
		return fechaC;
	}

	public void setFechaC(Date fechaC) {
		this.fechaC = fechaC;
	}

	public Date getFechaB() {
		return fechaB;
	}

	public void setFechaB(Date fechaB) {
		this.fechaB = fechaB;
	}

	public Categoria() {
	}

	public Categoria(Long id, @NotEmpty(message = "Ingrese un nombre") String nombre,
			@NotEmpty(message = "Ingrese una descripción") String descripcion,
			@Past(message = "Fecha de creacion no correcta") Date fechaC,
			@Past(message = "Fecha de creacion no correcta") Date fechaB) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaC = fechaC;
		this.fechaB = fechaB;
	}

	
}
