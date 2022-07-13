package com.hotelbeds.supplierintegrations.hackertest.application.logger;

public class LoggerToFile implements LoggerFactory {

	@Override
	public void logBadIp(String line) {
		System.out.println("BadLine -> " + line);
	}

}
