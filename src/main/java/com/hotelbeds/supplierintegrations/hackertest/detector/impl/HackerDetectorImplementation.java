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
		
		return line;
	}
}
