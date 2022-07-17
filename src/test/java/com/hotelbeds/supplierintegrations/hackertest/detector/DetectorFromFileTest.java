package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.hotelbeds.supplierintegrations.hackertest.application.detector.DetectorFromFile;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

@ExtendWith(MockitoExtension.class)
public class DetectorFromFileTest {
	
	private static final String IP_192_168_1_0 = "192.168.1.0";

	@InjectMocks
	DetectorFromFile detectorFromFile;

	@BeforeEach
	private void initTest() {
		ReflectionTestUtils.setField(detectorFromFile, "rutaAlmacenIps", "/ips");
	}
	
	@Test
	public void returnFalse() {
		
		assertThat(detectorFromFile.analizeIp(new Ip(IP_192_168_1_0), LocalDateTime.now())).as("returnFalse").isFalse();
	}
}
