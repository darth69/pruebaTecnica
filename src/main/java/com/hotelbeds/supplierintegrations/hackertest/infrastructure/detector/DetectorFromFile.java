package com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.application.detector.DetectorEngine;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventReader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.UtilsFile;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetectorFromFile implements DetectorFactory, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3127105774369678223L;

	@Autowired
	private FileEventReader fileEventReader;
	
	@Autowired
	private UtilsDateTime utilsDateTime;
	
	@Autowired
	private UtilsFile utilsFile;
	
	@Autowired
	private FileEventWriter fileEventWriter;
	
	@Autowired
	private ConfigLoader configLoader;
	
	@Autowired
	private DetectorEngine detectorEngine;
		
	@Override
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime) {
		
		//Crear Objeto para tratamiento del almacen
		utilsFile.crearDirectorios(configLoader.getRutaAlmacenIps());
		
		// Crear Objeto para cargar los eventos
		File file = new File(configLoader.getRutaAlmacenIps() + "/" + ip.getIp() + ".txt");		
				
		//Cargar eventos
		List<LocalDateTime> events = fileEventReader.recoveryEventsForIp(file).stream().map(utilsDateTime::parseLocalDateTimeEvent).collect(Collectors.toList());
		
		//Añadir evento a la lista
		events.add(eventDateTime);

		//Detectar resultado
		boolean res = detectorEngine.detectIp(events, eventDateTime);
				
		//Ordenar elementos para la persistencia de mayor a menor
		events = utilsDateTime.orderLocalDateTimeList(events);
		
		//Persistir la lista		
		try {
			fileEventWriter.writeEvents(file, events);
		} catch (IOException e) {
			log.error("Error al persisitir el fichero ->" + file.getAbsolutePath(), e);
		}		
		
		//retornar Resultado
		return res;
	}	
}