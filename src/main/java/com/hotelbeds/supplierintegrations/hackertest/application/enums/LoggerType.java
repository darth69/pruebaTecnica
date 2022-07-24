package com.hotelbeds.supplierintegrations.hackertest.application.enums;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerFactory;

public enum LoggerType {
	LOGGER2LOG(){
		
		private LoggerFactory loggerFactory;
		
		@Override
		public LoggerFactory getLogger() {
			return loggerFactory;
		}

		@Override
		public void setTLogger(LoggerFactory loggerFactory) {
			this.loggerFactory = loggerFactory;
		}		
	};
	
	public abstract LoggerFactory getLogger();
	
	public abstract void setTLogger(LoggerFactory loggerFactory);
}
