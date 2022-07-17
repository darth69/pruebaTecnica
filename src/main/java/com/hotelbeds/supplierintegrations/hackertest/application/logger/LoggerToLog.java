package com.hotelbeds.supplierintegrations.hackertest.application.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerToLog implements LoggerFactory {

	@Override
	public void logBadParseLine(String line, Exception e) {
		StringBuilder sb = new StringBuilder();
		if(null != e) {
			sb.append(e.getClass().toString() + " !!!! " + e.getMessage() + " -> ");
		}
		sb.append(line);
		log.error(sb.toString());
	}

}
