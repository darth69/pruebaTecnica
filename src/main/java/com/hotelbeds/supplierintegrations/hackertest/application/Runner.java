package com.hotelbeds.supplierintegrations.hackertest.application;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Runner implements CommandLineRunner{

	@Autowired
	private HackerDetector hackerDetector;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Cargamos fichero de pruebas para realizar la prueba 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("files/prueba.txt").getFile());
		List<String> events = Files.readAllLines(file.toPath(), Charset.defaultCharset());
		
		//Recorremos los eventos para detectar intrusos
		events.stream().forEach(event ->{
			log.debug("Analizando evento ->" + event);
			if(null !=  hackerDetector.parseLine(event)) {
				log.warn("!!!! INTRUSION DETECTADA !!!! >>>> " + event);
			}
		});
	}
}
