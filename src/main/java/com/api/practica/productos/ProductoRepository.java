package com.api.practica.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	public List<Producto> findAllByNombreContaining(String nombre);
	
}
