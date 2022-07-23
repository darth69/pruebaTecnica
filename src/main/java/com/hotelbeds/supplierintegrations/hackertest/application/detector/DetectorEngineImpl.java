package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;

@Service
public class DetectorEngineImpl implements DetectorEngine{
	
	
	
	@Autowired
	private ConfigLoader configLoader;
	
	@Override
	public boolean detectIp(List<LocalDateTime> events, LocalDateTime eventDateTime) {
		Long res = events.stream().map(event -> ChronoUnit.SECONDS.between(event, eventDateTime))
				.filter(event -> event >= configLoader.getMinLimit() && event <= configLoader.getMaxLimit())				
				.count();
		
		return res >= configLoader.getRetryLimit(); 
	}
}
