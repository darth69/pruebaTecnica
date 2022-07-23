package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.impl;

import java.io.File;
import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.UtilsFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilsFileImpl implements UtilsFile , Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221544L;
	
	
	@Override
	public void crearDirectorios(String ruta) {
		
		//Crear Objeto para tratamiento del almacen
				File dir = new File(ruta);
				
		//Si no existe el directorio lo creamos
				if(!dir.exists()) {
					log.info("Creando directorio inexistente -> " + ruta);
					dir.mkdirs();
					log.info("Directorio " + ruta + " creado");
				}
	}

}
