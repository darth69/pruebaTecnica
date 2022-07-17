package com.hotelbeds.supplierintegrations.hackertest.logger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.application.logger.LoggerToLog;

@ExtendWith(MockitoExtension.class)
public class LoggerToLogTest {
	
	@InjectMocks
	LoggerToLog service;
	
	@Test
	public void  logBadParseLine() {
		NullPointerException exception = new NullPointerException("test");
		assertThat(service.logBadParseLine("test", exception)).as("logBadParseLine").isEqualTo("class java.lang.NullPointerException !!!! test -> test");
	}
}