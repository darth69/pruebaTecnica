package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.time.LocalDateTime;
import java.util.List;

public interface DetectorEngine {

	boolean detectIp(List<LocalDateTime> events, LocalDateTime eventDateTime);

}
