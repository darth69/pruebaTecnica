package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileEventReaderImpl implements FileEventReader{
	
	@Override
	public ArrayList<String> recoveryEventsForIp(File file) {
		ArrayList<String> events = new ArrayList<>();
		if(!file.exists()) {
			return events;
		}
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();				
		}
		log.info("Ips cargadas en memoria");
		return events;
	}

}
