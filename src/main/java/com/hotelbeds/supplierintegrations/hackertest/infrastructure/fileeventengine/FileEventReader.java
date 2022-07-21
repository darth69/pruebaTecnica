package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface FileEventReader {

	ArrayList<String> recoveryEventsForIp(File file);

}
