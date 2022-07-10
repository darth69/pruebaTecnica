package com.hotelbeds.pruebaTecnica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PruebaTecnicaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
		
		log.info(">>>> Iniciando Prueba Tecnica");

		
		log.info("<<<< Finalizando Prueba Tecnica");
		
	}

}
