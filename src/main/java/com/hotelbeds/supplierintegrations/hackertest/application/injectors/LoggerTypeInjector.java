package com.hotelbeds.supplierintegrations.hackertest.application.injectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.application.enums.LoggerType;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerToLog;

//Injector de loggers en los enums al inicio
@Component
public class LoggerTypeInjector {
	@Autowired        
	private LoggerToLog loggerToLog;        
	
	@PostConstruct        
	public void postConstruct() {            
		LoggerType.LOGGER2LOG.setTLogger(loggerToLog);
	}
}
