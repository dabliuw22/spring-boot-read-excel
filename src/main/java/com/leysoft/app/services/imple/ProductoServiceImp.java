package com.leysoft.app.services.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leysoft.app.entitys.Producto;
import com.leysoft.app.repositorys.inter.ProductoRepository;
import com.leysoft.app.services.inter.ProductoService;

@Service @Transactional
public class ProductoServiceImp implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Transactional
	@Override
	public void save(Producto producto) {
		productoRepository.save(producto);
	}

	@Transactional(readOnly = true)
	@Override
	public Producto findById(Long id) {
		return productoRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Producto findByNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Transactional
	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Producto producto = findById(id);
		if(producto != null) {
			productoRepository.delete(id);
		}
	}
}