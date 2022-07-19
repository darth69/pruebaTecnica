package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.application.fileprocesors.FileEventReader;
import com.hotelbeds.supplierintegrations.hackertest.application.utils.datetime.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.application.utils.file.UtilsFile;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetectorFromFile implements DetectorFactory {

	private static final long TRESCIENTOS_PLUS = 300L;

	private static final long TRECIENTOS_MINUS = -300L;

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
		
		Long res = events.stream().map(event -> ChronoUnit.SECONDS.between(event, eventDateTime))
				.filter(event -> event >= TRECIENTOS_MINUS && event <= TRESCIENTOS_PLUS)				
				.count();
		
		return res > 0;		
	}	
}