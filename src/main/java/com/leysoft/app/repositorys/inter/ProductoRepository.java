package com.leysoft.app.repositorys.inter;

import org.springframework.data.repository.CrudRepository;

import com.leysoft.app.entitys.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	
	public Producto findByNombre(String nombre);
}
