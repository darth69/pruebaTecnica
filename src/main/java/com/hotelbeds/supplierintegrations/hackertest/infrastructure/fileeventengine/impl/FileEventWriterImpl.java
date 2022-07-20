package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

public class FileEventWriterImpl implements FileEventWriter{

	@Override
	public boolean writeEvents(Ip ip, List<LocalDateTime> events) {
		// TODO Auto-generated method stub
		return false;
	}

}
