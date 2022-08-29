package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

}
