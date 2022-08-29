package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "proveedores")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Ingrese el nombre del proveedor")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;
	@Size(min = 9, max = 9)
	@NotEmpty(message = "Ingresar el numero de contacto")
	@Column(name = "numero", nullable = false, length = 9)
	private String numero;
	@ManyToOne
	@JoinColumn(name = "ciudad_id", nullable = false)
	private Ciudad ciudad;
	@Size(min = 6, max = 6)
	@NotEmpty(message = "Ingresar el identificador del proveedor")
	@Column(name = "identificador", nullable = false, length = 6)
	private String identificador;
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Proveedor(Long id, @NotEmpty(message = "Ingrese el nombre del proveedor") String nombre,
			@Size(min = 9, max = 9) @NotEmpty(message = "Ingresar el numero de contacto") String numero, Ciudad ciudad,
			@Size(min = 6, max = 6) @NotEmpty(message = "Ingresar el identificador del proveedor") String identificador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numero = numero;
		this.ciudad = ciudad;
		this.identificador = identificador;
	}
	public Proveedor() {
		super();
	}

}
