package com.hotelbeds.supplierintegrations.hackertest.application.enums;

import com.hotelbeds.supplierintegrations.hackertest.application.logger.LoggerFactory;
import com.hotelbeds.supplierintegrations.hackertest.application.logger.LoggerToFile;

public enum LoggerType {
	FileLogger(){
		@Override
		public LoggerFactory getLogger() {
			return new LoggerToFile();
		}		
	};
	
	public abstract LoggerFactory getLogger();
}
