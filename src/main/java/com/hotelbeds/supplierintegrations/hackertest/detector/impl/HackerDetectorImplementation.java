package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HackerDetectorImplementation implements HackerDetector{

	@Override
	public String parseLine(String line) {
		// If guarda para evitar fallos en caso de que la linea sea nula
		if(null == line) {
			return null;
		}
		
		// dividir la linea para capturar los datos
		String[] campos = line.split(",");
		
		// Verificar que la linea tiene 4 campos
		if(campos.length != 4) {
			log.error("Faltan campos de datos en linea " +line);
			return null;
		}
		
		
		
		return line;
	}
}
