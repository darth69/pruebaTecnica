package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.application.enums.LoggerType;
import com.hotelbeds.supplierintegrations.hackertest.application.logger.LoggerFactory;
import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

import lombok.extern.slf4j.Slf4j;

public class HackerDetectorImplementation implements HackerDetector{
	
	@Value("${logger.type}")
	private String loggerType;
	
	

	@Override
	public String parseLine(String line) {
		
		LoggerFactory logger = LoggerType.valueOf(loggerType).getLogger();
		
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
		
		try {
			//Creamos la IP desde el primer campo
			Ip ip = new Ip(campos[0]);
		} catch (Exception e) {
			logger.logBadParseLine(line, e);
			return null;
		} 
		
		//Convertir el EPOCH a LocalDateTime
		
		try {
			Long epoch = Long.parseLong(campos[0]);
			
			LocalDateTime localDateTimeOfEvent = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
		} catch (NumberFormatException e) {
			
			logger.logBadParseLine(line, e);
			return null;
		}
		
		
		
		//Detectar si es OK o KO el login
		
		//Analizar la linea para detectar intento de hack
		
		return line;
	}
}
