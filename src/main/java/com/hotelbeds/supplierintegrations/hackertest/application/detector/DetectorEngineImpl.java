package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;

@Service
public class DetectorEngineImpl implements DetectorEngine, Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221550L;
	
	@Autowired
	private ConfigLoader configLoader;
	
	@Override
	public boolean detectIp(List<LocalDateTime> events, LocalDateTime eventDateTime) {
		//Contamos los eventos fallados entre los limites configurados
		Long res = events.stream().map(event -> ChronoUnit.SECONDS.between(event, eventDateTime))
				.filter(event -> event >= configLoader.getMinLimit() && event <= configLoader.getMaxLimit())				
				.count();
		//devolvemos si el calculo supera el limite de fallos.
		return res >= configLoader.getRetryLimit(); 
	}
}
