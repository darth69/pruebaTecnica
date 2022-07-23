package com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoggerToLog implements LoggerFactory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887389928830987490L;

	@Override
	public String logBadParseLine(String line, Exception e) {
		
		return logData(line, e, "BAD PARSE LINE");
	}
	
	@Override
	public String logException(String line, Exception e) {
		return logData(line, e, "EXCEPTION");
	}
	
	private String logData(String line, Exception e, String head) {
		StringBuilder sb = new StringBuilder();
		if(null != e) {
			sb.append(head + " >>>> " + e.getClass().toString() + " !!!! " + e.getMessage() + " -> ");
		}
		sb.append(line);
		log.error(sb.toString());
		return sb.toString();
	}
}
