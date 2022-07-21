package com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.application.detector.DetectorEngine;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventReader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.UtilsFile;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetectorFromFile extends DetectorEngine implements DetectorFactory {

	@Value("${detector.ip.storage.path}")
	private String rutaAlmacenIps;
	
	@Autowired
	private FileEventReader fileEventReader;
	
	@Autowired
	private UtilsDateTime utilsDateTime;
	
	@Autowired
	private UtilsFile utilsFile;
	
	@Autowired
	private FileEventWriter fileEventWriter;
		
	@Override
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime) {
		
		//Crear Objeto para tratamiento del almacen
		utilsFile.CrearDirectorios(rutaAlmacenIps);
		
		// Crear Objeto para cargar los eventos
		File file = new File(rutaAlmacenIps + "/" + ip.getIp() + ".txt");		
				
		//Cargar eventos
		List<LocalDateTime> events = fileEventReader.recoveryEventsForIp(file).stream().map(utilsDateTime::parseLocalDateTimeEvent).collect(Collectors.toList());
		
		//Detectar resultado
		boolean res = detectIp(events, eventDateTime);
		
		//Cargar evento a la lista
		
		events.add(eventDateTime);
		
		//Ordenar elementos para la persistencia de mayor a menor
		events = utilsDateTime.orderLocalDateTimeList(events);
		
		//Persistir la lista		
		try {
			fileEventWriter.writeEvents(file, events);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		//retornar Resultado
		return res;
	}	
}