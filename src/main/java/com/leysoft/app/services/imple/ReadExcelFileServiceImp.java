package com.leysoft.app.services.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.app.entitys.Producto;
import com.leysoft.app.services.inter.ProductoService;
import com.leysoft.app.services.inter.ReadExcelFileService;

@Service
public class ReadExcelFileServiceImp implements ReadExcelFileService {

	@Autowired
	private ProductoService productoService; 
	
	@Override
	public boolean read(String nombre) throws IOException {
		FileInputStream file = new FileInputStream(new File(Paths.get("files").resolve(nombre).toAbsolutePath().toUri()));
		String ext = getExtension(nombre);
		if(ext.equals("xlsx")) {
			return xssf(file);
		} else if(ext.equals("xls")) {
			return hssf(file);
		}
		file.close();
		return false;
	}
	
	/*Para archivos de Excel 2009 y superiores*/
	public boolean xssf(FileInputStream file) throws IOException {
		boolean estado = false;
		Workbook libro = new XSSFWorkbook(file);
		Sheet hoja = libro.getSheetAt(0);
		Iterator<Row> filas = hoja.iterator();
		int k = 0;
		while(filas.hasNext()) {
			Row row = filas.next();
			if(k > 0) {
				List<Cell> celdas = IteratorUtils.toList(row.iterator());
				int c = 0;
				Producto producto = new Producto();
				for(Cell celda: celdas) {
					switch (c) {
					case 0:
						producto.setNombre(celda.getStringCellValue());
						break;
					case 1:
						producto.setCodigo(celda.getStringCellValue());
						break;
					default:
						break;
					}
					productoService.save(producto);
					estado = true;
					c++;
				}
			}
			k++;
		}
		libro.close();
		return estado;
	}
	
	/*Para archivos de Excel 2003 e inferiores*/
	public boolean hssf(FileInputStream file) throws IOException {
		boolean estado = false;
		/*Workbook libro = new HSSFWorkbook(file);
		Sheet hoja = libro.getSheetAt(0);
		Iterator<Row> filas = hoja.iterator();
		filas.forEachRemaining(fila -> {
			Iterator<Cell> celdas = fila.iterator();
			celdas.forEachRemaining(celda -> {
				switch (celda.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.println(celda.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.println(celda.getNumericCellValue());
					break;
				default:
					break;
				}
			});
		});
		libro.close();*/
		return estado;
	}
	
	public String getExtension(String nombre) {
		int i = nombre.lastIndexOf('.');
		if(i > 0) {
			return nombre.substring(i+1);
		}
		return "";
	}
}
