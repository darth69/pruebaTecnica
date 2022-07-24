package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface FileEventWriter extends Serializable{
	
	boolean writeEvents(File file, List<LocalDateTime> events) throws IOException;
}
