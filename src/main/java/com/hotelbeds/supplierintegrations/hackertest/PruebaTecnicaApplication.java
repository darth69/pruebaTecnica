package com.hotelbeds.supplierintegrations.hackertest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.detector.impl.HackerDetectorImpl;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PruebaTecnicaApplication {
	
	@Autowired
	HackerDetector hackerDetector;
	
	public static void main(String[] args) {
		log.info(">>>> Iniciando Prueba Tecnica");

		SpringApplication.run(PruebaTecnicaApplication.class, args);
		
		
		log.info("<<<< Finalizando Prueba Tecnica");
		
	}

}
