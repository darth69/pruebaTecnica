package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface FileEventWriter {
	
	boolean writeEvents(File file, List<LocalDateTime> events) throws IOException;
}
