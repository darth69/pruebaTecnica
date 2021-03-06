package com.hotelbeds.supplierintegrations.hackertest.logger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.logger.LoggerToLog;

@ExtendWith(MockitoExtension.class)
class LoggerToLogTest {
	
	@InjectMocks
	LoggerToLog service;
	
	@Test
	void  logBadParseLine() {
		NullPointerException exception = new NullPointerException("test");
		assertThat(service.logBadParseLine("test", exception)).as("logBadParseLine").isEqualTo("BAD PARSE LINE >>>> class java.lang.NullPointerException !!!! test -> test");
	}
}