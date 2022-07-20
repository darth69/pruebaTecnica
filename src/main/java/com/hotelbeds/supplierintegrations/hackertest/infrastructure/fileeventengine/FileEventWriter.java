package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine;

import java.time.LocalDateTime;
import java.util.List;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

public interface FileEventWriter {

	public boolean writeEvents(Ip ip, List<LocalDateTime> events);
}
