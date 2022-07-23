package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class FileEventReaderImpl implements FileEventReader{
	
	@Autowired
	private ConfigLoader configLoader;
	
	private LoggerFactory logger;
	
	@Override
	public ArrayList<String> recoveryEventsForIp(File file) {
		ArrayList<String> events = new ArrayList<>();
		if(!file.exists()) {
			return events;
		}
		
		//Se controla si logger es nulo para poder ejecutar los test unitarios
		String loggerType = configLoader.getLoggerType();
		if(!loggerType.isEmpty()) {
			logger = LoggerType.valueOf(loggerType).getLogger();
		}
		
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
			logger.logException("Fichero no encontrado -> " + file.getAbsolutePath(), e);			
		} catch (IOException e) {
			logger.logException("Error al cargar fichero -> " + file.getAbsolutePath(), e);			
		}
		log.debug("Ips cargadas en memoria");
		return events;
	}

}
