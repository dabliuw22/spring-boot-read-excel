package com.leysoft.app.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.leysoft.app.entitys.ExcelFile;
import com.leysoft.app.models.ExcelFileModel;
import com.leysoft.app.services.inter.ExcelFileService;
import com.leysoft.app.services.inter.ReadExcelFileService;
import com.leysoft.app.services.inter.UploadService;
import com.leysoft.app.validators.ExcelFileModelValidator;

@Controller
public class IndexController {
	
	private static Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private ExcelFileService excelFileService;
	
	@Autowired
	private ReadExcelFileService readExcelFileService;
	
	@Autowired
	private ExcelFileModelValidator validator;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("excel", new ExcelFileModel());
		return "index";
	}
	
	@InitBinder("excel")
	public void initBinderExcel(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@PostMapping("/")
	public String index(@Valid @ModelAttribute("excel") ExcelFileModel excel, BindingResult result) throws IOException {
		validator.validate(excel, result);
		if(!result.hasErrors()) {
			LOG.info(excel.getNombre().getContentType());
			String nombre = uploadService.save(excel.getNombre());
			ExcelFile excelFile = new ExcelFile();
			excelFile.setNombre(nombre);
			excelFile.setType(excel.getNombre().getContentType());
			excelFile.setEstado(readExcelFileService.read(nombre));
			excelFileService.save(excelFile);
			return "redirect:/";
		}
		
		return "index";
	}
}
