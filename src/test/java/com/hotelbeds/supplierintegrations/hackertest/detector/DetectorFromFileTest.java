package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.application.detector.DetectorFromFile;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

@ExtendWith(MockitoExtension.class)
public class DetectorFromFileTest {
	
	@InjectMocks
	DetectorFromFile detectorFromFile;

	@Test
	public void returnFalse() {
		assertThat(detectorFromFile.analizeIp(new Ip("192.168.1.0"), LocalDateTime.now())).as("returnFalse").isFalse();
	}
}
