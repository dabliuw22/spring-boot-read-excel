package com.leysoft.app.services.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leysoft.app.entitys.ExcelFile;
import com.leysoft.app.repositorys.inter.ExcelFileRepository;
import com.leysoft.app.services.inter.ExcelFileService;

@Service @Transactional
public class ExcelFileServiceImp implements ExcelFileService {

	@Autowired
	private ExcelFileRepository excelFileRepository;
	
	@Transactional
	@Override
	public void save(ExcelFile excel) {
		excelFileRepository.save(excel);
	}

	@Transactional(readOnly = true)
	@Override
	public ExcelFile findById(Long id) {
		return excelFileRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ExcelFile> findAll() {
		return (List<ExcelFile>) excelFileRepository.findAll();
	}

	@Transactional
	@Override
	public void update(ExcelFile excel) {
		excelFileRepository.save(excel);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		ExcelFile excel = findById(id);
		if(excel != null) {
			excelFileRepository.delete(id);
		}
	}
}