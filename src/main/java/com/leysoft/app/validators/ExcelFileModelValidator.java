package com.leysoft.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.leysoft.app.models.ExcelFileModel;

@Component
public class ExcelFileModelValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExcelFileModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ExcelFileModel file = (ExcelFileModel) obj;
		if(file.getNombre() != null) {
			if(file.getNombre().isEmpty()) {
				errors.rejectValue("nombre", "file.nombre", "No puede estar vacio");
			}
			if(!file.getNombre().getContentType().contains("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				errors.rejectValue("nombre", "file.nombre", "Debe ser formato excel");
			}
			if(file.getNombre().getSize() > 20*1024*1024) {
				errors.rejectValue("nombre", "file.nombre", "El archivo excede los 20MB");
			}
			
		}
	}

}
