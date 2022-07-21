package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class DetectorEngine {
	
	@Value("${detector.ip.max.limit}")
	private long maxLimit;

	@Value("${detector.ip.min.limit}")
	private long minLimit;

	@Value("${detector.ip.retry.limit}")
	private long retryLimit;
	
	public boolean detectIp(List<LocalDateTime> events, LocalDateTime eventDateTime) {
		Long res = events.stream().map(event -> ChronoUnit.SECONDS.between(event, eventDateTime))
				.filter(event -> event >= minLimit && event <= maxLimit)				
				.count();
		return res >= retryLimit;
	}
}
