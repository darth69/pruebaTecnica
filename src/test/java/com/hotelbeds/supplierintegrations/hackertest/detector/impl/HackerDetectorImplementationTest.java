package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class HackerDetectorImplementationTest {

	@InjectMocks
	private HackerDetectorImplementation hackerDetectorImplementation;
	
	@Test
	public void shouldreturnNull() {
		assertThat(hackerDetectorImplementation.parseLine(null)).as("shouldreturnNull").isNull();
	}
	
}
