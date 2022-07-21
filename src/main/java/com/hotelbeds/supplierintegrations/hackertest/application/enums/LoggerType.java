package com.hotelbeds.supplierintegrations.hackertest.application.enums;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerFactory;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerToLog;

public enum LoggerType {
	LOGGER2LOG(){
		@Override
		public LoggerFactory getLogger() {
			return new LoggerToLog();
		}		
	};
	
	public abstract LoggerFactory getLogger();
}
