package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class HackerDetectorImplementationTest {

	private static final String LINE = "192.168.1.0,b,c,d";
	@InjectMocks
	private HackerDetectorImplementation hackerDetectorImplementation;
	
	@BeforeEach
	private void initTest() {
		ReflectionTestUtils.setField(hackerDetectorImplementation, "loggerType", "Logger2Log");
	}
	
	@Test
	public void shouldreturnNull() {
		assertThat(hackerDetectorImplementation.parseLine(null)).as("shouldreturnNull").isNull();
	}
	
	@Test
	public void shouldreturnNull3campos() {
		assertThat(hackerDetectorImplementation.parseLine("a,b,c")).as("shouldreturnNull").isNull();
	}

	@Test
	public void shouldreturnNullBadIp() {
		assertThat(hackerDetectorImplementation.parseLine("a,b,c,d")).as("shouldreturnNullBadIp").isNull();
	}
	
	@Test
	public void shouldreturnLine() {
		assertThat(hackerDetectorImplementation.parseLine(LINE)).as("shouldreturnLine").isEqualTo(LINE);
	}
	
	
}
