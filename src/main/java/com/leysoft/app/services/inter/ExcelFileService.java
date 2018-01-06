package com.leysoft.app.services.inter;

import java.util.List;

import com.leysoft.app.entitys.ExcelFile;

public interface ExcelFileService {
	
	public void save(ExcelFile excel);
	
	public ExcelFile findById(Long id);
	
	public List<ExcelFile> findAll();
	
	public void update(ExcelFile excel);
	
	public void delete(Long id);
}