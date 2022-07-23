package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.application.enums.DetectorType;
import com.hotelbeds.supplierintegrations.hackertest.application.enums.LoggerType;
import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector.DetectorFactory;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerFactory;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

@Service
public class HackerDetectorImpl implements HackerDetector, Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221549L;
	
	
	private static final String SIGNIN_SUCCESS = "SIGNIN_SUCCESS";
	private static final String SIGNIN_FAILURE = "SIGNIN_FAILURE";
	
	@Autowired
	ConfigLoader configLoader;
	
	@Autowired
	private UtilsDateTime utilsDateTime;	
	
	private DetectorFactory detector;
	
	private LoggerFactory logger;
	
	private Ip ip;
	
	private LocalDateTime eventDateTime;
	
	@Override
	public String parseLine(String line) {
		
		//Se controla si logger es nulo para poder ejecutar los test unitarios
		String loggerType = configLoader.getLoggerType();
		if(!loggerType.isEmpty()) {
			logger = LoggerType.valueOf(loggerType).getLogger();
		}		

		//Se controla si detector es nulo para poder ejecutar los test unitarios
		String detectorType = configLoader.getDetectorType();
		if(!detectorType.isEmpty()) {
			detector = DetectorType.valueOf(detectorType).getDetector();
		}
		// If guarda para evitar fallos en caso de que la linea sea nula
		if(null == line) {
			return null;
		}
		
		// dividir la linea para capturar los datos
		String[] campos = line.split(",");
		
		// Verificar que la linea tiene 4 campos
		if(campos.length != 4) {
			logger.logBadParseLine("Faltan campos de datos en linea " + line, null);
			return null;
		}
		
		//Verificamos si es Login OK no continuamos
		if(SIGNIN_SUCCESS.equals(campos[2])) {
			return null;
		}
		
		//Si el login no es KO es un formato no reconocido y lanzamos error		
		if(!SIGNIN_FAILURE.equals(campos[2])) {
			logger.logBadParseLine(line, new IllegalArgumentException("Opción de login no valida"));
			return null;
		}
				
		//Creamos la IP desde el primer campo
		try {
			ip = new Ip(campos[0]);
		} catch (Exception e) {
			logger.logBadParseLine(line, e);
			return null;
		} 
		
		//Convertir el EPOCH a LocalDateTime
		try {
			eventDateTime = utilsDateTime.parseLocalDateTimeEvent(campos[1]);
		} catch (NumberFormatException e) {			
			logger.logBadParseLine(line, e);
			return null;
		}
		
		//Analizar la linea para detectar intento de hack		
		if(detector.analizeIp(ip, eventDateTime)) {
			return line;			
		}
		
		return null;
		
	}
}
