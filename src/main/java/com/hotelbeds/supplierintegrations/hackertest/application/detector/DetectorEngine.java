package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface DetectorEngine extends Serializable{

	boolean detectIp(List<LocalDateTime> events, LocalDateTime eventDateTime);

}
