package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.application.utils.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetectorFromFile implements DetectorFactory {

	@Value("${detector.ip.storage.path}")
	private String rutaAlmacenIps;
		
	@Override
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime) {
		
		File dir = new File(rutaAlmacenIps);
		
		if(!dir.exists()) {
			log.info("Creando directorio inexistente -> " + rutaAlmacenIps);
			dir.mkdirs();
			log.info("Directorio " + rutaAlmacenIps + " creado");
		}
		
		File file = new File(rutaAlmacenIps + "/" + ip.getIp() + ".txt");		
				
		List<LocalDateTime> events = recoveryEventsForIp(file).stream().map(UtilsDateTime::parseLocalDateTimeEvent).collect(Collectors.toList());
		
		return false;
	}

	private ArrayList<String> recoveryEventsForIp(File file) {
		ArrayList<String> events = new ArrayList<>();
		if(file.exists()) {
			log.info("Cargando Ips a memoria");
			BufferedReader bufferedReader;			
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				String line;
				while((line = bufferedReader.readLine()) != null) { 
					events.add(line);
					log.debug("Epoch Cargado -> " + line);
					}
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
			log.info("Ips cargadas en memoria");			
		}
		return events;
	}
}