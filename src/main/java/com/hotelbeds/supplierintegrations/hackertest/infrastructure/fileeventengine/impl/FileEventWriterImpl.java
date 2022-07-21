package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@Service
public class FileEventWriterImpl implements FileEventWriter{
	
	@Autowired
	private UtilsDateTime utilsDateTime;
	
	@Value("${fileeventwriter.limit}")
	private long limit;

	@Override
	public boolean writeEvents(File file, List<LocalDateTime> events) throws IOException {
		FileWriter fw = new FileWriter(file);
				
		List<String> epochList = events.stream()
			.map(utilsDateTime::localDateTimeToEpoch)
			.map(x -> x.toString())
			.limit(limit)
			.collect(Collectors.toList());
		
		for (String epoch : epochList) {
			fw.write(epoch + System.lineSeparator());
		}
			
		fw.close();
		
		return true;
	}

}
