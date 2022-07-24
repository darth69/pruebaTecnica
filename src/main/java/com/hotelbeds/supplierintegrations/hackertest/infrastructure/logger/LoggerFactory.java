package com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger;

import java.io.Serializable;

public interface LoggerFactory extends Serializable{
	
	public String logBadParseLine(String line, Exception e);

	public String logException(String line, Exception e);	

}
