package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.detector.impl.HackerDetectorImpl;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector.DetectorFromFile;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@ExtendWith(MockitoExtension.class)
class HackerDetectorImplTest {
	
	private static final String LOGGER2LOG_STRING = "LOGGER2LOG";
	private static final String IP = "192.168.1.0";
	private static final String BAD_EPOCH = "92233720368547758071";	
	private static final Long EPOCH_NOW = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);	
	private static final String EPOCH = EPOCH_NOW.toString();	
	private static final String SIGNIN_SUCCESS = "SIGNIN_SUCCESS";
	private static final String SIGNIN_FAILURE = "SIGNIN_FAILURE";
	private static final String LINE_BAD_EPOCH = IP + "," + BAD_EPOCH + "," + SIGNIN_FAILURE +",d";
	private static final String LINE_OK_LOGIN = IP + "," + EPOCH + "," + SIGNIN_SUCCESS + ",d";
	private static final String LINE_LOGIN_FAIL = IP + "," + EPOCH + "," + "c" + ",d";
	private static final String LINE_OK = IP + "," + EPOCH + "," + SIGNIN_FAILURE + ",d";
	
	
	@InjectMocks
	private HackerDetectorImpl hackerDetectorImplementation;
	
	@Mock
	private UtilsDateTime utilsDateTime;
	
	@Mock
	private ConfigLoader configLoader;
	
	@Mock
	private DetectorFromFile detectorFromFile;
	
	@BeforeEach
	private void initTest() {
		when(configLoader.getLoggerType()).thenReturn(LOGGER2LOG_STRING);		
		when(configLoader.getDetectorType()).thenReturn("");	
	}	
	
	@Test
	void shouldreturnNull() {
		assertThat(hackerDetectorImplementation.parseLine(null)).as("shouldreturnNull").isNull();
	}
	
	@Test
	void shouldreturnNull3campos() {
		assertThat(hackerDetectorImplementation.parseLine("a,b,c")).as("shouldreturnNull").isNull();
	}

	@Test
	void shouldreturnNullBadIp() {
		assertThat(hackerDetectorImplementation.parseLine("a,b," + SIGNIN_FAILURE + ",d")).as("shouldreturnNullBadIp").isNull();
	}

	@Test
	void shouldreturnNullBadEPOCH() {
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenThrow(new NumberFormatException());
		assertThat(hackerDetectorImplementation.parseLine(LINE_BAD_EPOCH)).as("shouldreturnNullBadEPOCH").isNull();
	}

	@Test
	void shouldreturnNullLoginOk() {
		assertThat(hackerDetectorImplementation.parseLine(LINE_OK_LOGIN)).as("shouldreturnNullLoginOk").isNull();
	}
	
	@Test
	void shouldreturnNullLoginFail() {
		assertThat(hackerDetectorImplementation.parseLine(LINE_LOGIN_FAIL)).as("shouldreturnNullLoginFail").isNull();
	}
	
	@Test
	void shouldreturnLine() {
		when(detectorFromFile.analizeIp(any(), any())).thenReturn(true);
		assertThat(hackerDetectorImplementation.parseLine(LINE_OK)).as("shouldreturnLine").isEqualTo(LINE_OK);
	}		
}
