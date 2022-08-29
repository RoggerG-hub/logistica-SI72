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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orden_compras")
public class OrdenCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "proveedor_id", nullable = false)
	private Proveedor proveedor;
	@NotEmpty(message = "Ingrese la serie")
	@Column(name = "serie", nullable = false, length = 70)
	private String serie;
	@NotEmpty(message = "Ingrese el correlativo")
	@Column(name = "correlativo", nullable = false, length = 70)
	private String correlativo;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}
	public OrdenCompra(Long id, Proveedor proveedor, @NotEmpty(message = "Ingrese la serie") String serie,
			@NotEmpty(message = "Ingrese el correlativo") String correlativo,
			@Past(message = "Fecha de creacion no correcta") Date fechaR) {
		super();
		this.id = id;
		this.proveedor = proveedor;
		this.serie = serie;
		this.correlativo = correlativo;
		this.fechaR = fechaR;
	}
	public OrdenCompra() {
	}
	
}
