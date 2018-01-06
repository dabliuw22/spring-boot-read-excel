package com.leysoft.app.services.inter;

import java.util.List;

import com.leysoft.app.entitys.Producto;

public interface ProductoService {
	
	public void save(Producto producto);
	
	public Producto findById(Long id);
	
	public Producto findByNombre(String nombre);
	
	public List<Producto> findAll();
	
	public void update(Producto producto);
	
	public void delete(Long id);
}