package com.hotelbeds.supplierintegrations.hackertest.application;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//Runner para lanzar los procesos
public class Runner implements CommandLineRunner{

	@Autowired
	private HackerDetector hackerDetector;
	
	@Autowired
	private UtilsDateTime utilsDateTime;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Cargamos fichero de pruebas para realizar la prueba 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("files/prueba.txt").getFile());
		List<String> events = Files.readAllLines(file.toPath(), Charset.defaultCharset());
		
		log.info("---------------------------------");
		log.info("PROCESAMIENTO DE LISTA DE EVENTOS");
		log.info("---------------------------------");
		//Recorremos los eventos para detectar intrusos
		events.stream().forEach(event ->{
			log.debug("Analizando evento ->" + event);
			if(null !=  hackerDetector.parseLine(event)) {
				log.warn("!!!! INTRUSION DETECTADA !!!! >>>> " + event);
			}
		});
		
		//Calculamos las diferencias de minutos
		Long cientoVeinte = utilsDateTime.diffBetweenDateTimes("Thu, 21 Dec 2000 16:01:00 +0400", "Thu, 21 Dec 2000 16:01:29 +0200");
		Long cientoVeintiuno = utilsDateTime.diffBetweenDateTimes("Thu, 21 Dec 2000 16:01:00 +0400", "Thu, 21 Dec 2000 16:01:30 +0200");
		
		//Sacar Resultados
		log.info("------------------------------------------");
		log.info("CALCULO DE DIFERENCIA DE FECHAS EN MINUTOS");
		log.info("------------------------------------------");
		log.info("Thu, 21 Dec 2000 16:01:00 +0400 - Thu, 21 Dec 2000 16:01:29 +0200 -> " + cientoVeinte );
		log.info("Thu, 21 Dec 2000 16:01:00 +0400 - Thu, 21 Dec 2000 16:01:30 +0200 -> " + cientoVeintiuno );
		
	}
}
