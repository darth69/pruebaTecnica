package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.application.enums.LoggerType;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventReader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//Servicio lector de eventos desde los ficheros
public class FileEventReaderImpl implements FileEventReader, Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221547L;
	
	
	@Autowired
	private ConfigLoader configLoader;
	
	private LoggerFactory logger;
	
	@Override
	public ArrayList<String> recoveryEventsForIp(File file) {
		//Generación de array para almacenar los eventos
		ArrayList<String> events = new ArrayList<>();
		//Si no existe el fichero se devuelven los eventos vacios.
		if(!file.exists()) {
			return events;
		}
		
		//Se controla si logger es nulo para poder ejecutar los test unitarios
		String loggerType = configLoader.getLoggerType();
		if(!loggerType.isEmpty()) {
			logger = LoggerType.valueOf(loggerType).getLogger();
		}
		
		//Carga de los registros en memoria
		log.debug("Cargando Ips a memoria");
		BufferedReader bufferedReader;			
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			while((line = bufferedReader.readLine()) != null) { 
				events.add(line);
				log.debug("Epoch Cargado -> " + line);
				}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			//Registrar si no se encuentra el fichero
			logger.logException("Fichero no encontrado -> " + file.getAbsolutePath(), e);			
		} catch (IOException e) {
			//Registrar fallos de acceso a ficheros
			logger.logException("Error al cargar fichero -> " + file.getAbsolutePath(), e);			
		}
		//Devolvemos eventos
		log.debug("Ips cargadas en memoria");
		return events;
	}

}
