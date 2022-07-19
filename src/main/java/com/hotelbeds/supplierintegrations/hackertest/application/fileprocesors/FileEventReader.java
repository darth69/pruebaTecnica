package com.hotelbeds.supplierintegrations.hackertest.application.fileprocesors;

import java.io.File;
import java.util.ArrayList;

public interface FileEventReader {

	ArrayList<String> recoveryEventsForIp(File file);

}
