package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@Service
//Servicio registrador de eventos en formato de ficheros
public class FileEventWriterImpl implements FileEventWriter, Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221546L;
		
	@Autowired
	private UtilsDateTime utilsDateTime;
		
	@Autowired
	ConfigLoader configLoader;

	@Override
	public boolean writeEvents(File file, List<LocalDateTime> events) throws IOException {
		//Creamos filewriter
		FileWriter fw = new FileWriter(file);
				
		//Convertimos los eventos en Epochs para consultarlos mas tarde y alamcenamos el limite de los retry
		List<String> epochList = events.stream()
			.map(utilsDateTime::localDateTimeToEpoch)
			.map(x -> x.toString())
			.limit(configLoader.getRetryLimit())
			.collect(Collectors.toList());
		
		//Volcamos a fichero
		for (String epoch : epochList) {
			fw.write(epoch + System.lineSeparator());
		}
		
		//cerramos fichero;	
		fw.close();
		
		return true;
	}

}
