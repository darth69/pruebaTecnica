package com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger;

public interface LoggerFactory {
	
	public String logBadParseLine(String line, Exception e);

	public String logException(String line, Exception e);	

}
