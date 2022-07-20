package com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.application.detector.DetectorEngine;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.FileEventReader;
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
		
	@Override
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime) {
		
		//Crear Objeto para tratamiento del almacen
		utilsFile.CrearDirectorios(rutaAlmacenIps);
		
		// Crear Objeto para cargar los eventos
		File file = new File(rutaAlmacenIps + "/" + ip.getIp() + ".txt");		
				
		//Cargar eventos
		List<LocalDateTime> events = fileEventReader.recoveryEventsForIp(file).stream().map(utilsDateTime::parseLocalDateTimeEvent).collect(Collectors.toList());
		
		return detectIp(events, eventDateTime);	
	}	
}