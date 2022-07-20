package com.hotelbeds.supplierintegrations.hackertest.infrastructure;

import java.io.File;
import java.util.ArrayList;

public interface FileEventReader {

	ArrayList<String> recoveryEventsForIp(File file);

}
