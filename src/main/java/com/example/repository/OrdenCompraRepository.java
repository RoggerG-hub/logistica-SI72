package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long>{
	OrdenCompra findByCorrelativoContainingIgnoreCase(String correlativo);
	@Query("SELECT count(p) FROM OrdenCompra p  WHERE UPPER(p.correlativo)=UPPER(?1)")
	int verificarExistencia(String correlativo);
}
