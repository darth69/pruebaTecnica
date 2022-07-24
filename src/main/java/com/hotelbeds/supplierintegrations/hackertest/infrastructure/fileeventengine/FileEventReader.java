package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public interface FileEventReader extends Serializable{

	ArrayList<String> recoveryEventsForIp(File file);

}
