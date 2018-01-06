package com.leysoft.app.services.imple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leysoft.app.services.inter.UploadService;

@Service
public class UploadServiceImp implements UploadService {

	@Override
	public String save(MultipartFile file) throws IOException {
		String nombre = UUID.randomUUID().toString() + file.getOriginalFilename();
		Path path = Paths.get("files").resolve(nombre).toAbsolutePath();
		Files.copy(file.getInputStream(), path);
		return nombre;
	}

	@Override
	public boolean delete(String nombre) {
		Path path = Paths.get("files").resolve(nombre).toAbsolutePath();
		if(path.toFile().exists() && path.toFile().canRead()) {
			path.toFile().delete();
		}
		return false;
	}
}