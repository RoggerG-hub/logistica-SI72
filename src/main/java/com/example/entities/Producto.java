package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Ingrese nombre del producto")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;
	
	@NotEmpty(message = "Ingrese una descripcion")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Min(0)
	@Column(name = "stock", nullable = false)
	int stock;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingresar el SKU del producto")
	@Column(name = "sku", nullable = false, length = 8)
	private String sku;
	@Size(min = 1, max = 8)
	@NotEmpty(message = "Ingresar la unidad de medida del producto")
	@Column(name = "unidad", nullable = false)
	private String unidad;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "almacen_id", nullable = false)
	Almacen almacen;
	@Past(message = "Fecha de registro no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Producto() {

	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}
	public Date getFechaB() {
		return fechaB;
	}
	public void setFechaB(Date fechaB) {
		this.fechaB = fechaB;
	}
	public Producto(Long id, @NotEmpty(message = "Ingrese nombre del producto") String nombre,
			@NotEmpty(message = "Ingrese una descripcion") String descripcion, @Min(1) int stock,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingresar el SKU del producto") String sku,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingresar la unidad de medida del producto") String unidad,
			Categoria categoria, Almacen almacen, @Past(message = "Fecha de registro no correcta") Date fechaR,
			@Past(message = "Fecha de baja no correcta") Date fechaB) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.sku = sku;
		this.unidad = unidad;
		this.categoria = categoria;
		this.almacen = almacen;
		this.fechaR = fechaR;
		this.fechaB = fechaB;
	}

}
