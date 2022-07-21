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
import org.springframework.test.util.ReflectionTestUtils;

import com.hotelbeds.supplierintegrations.hackertest.detector.impl.HackerDetectorImpl;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@ExtendWith(MockitoExtension.class)
public class HackerDetectorImplTest {

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
		assertThat(hackerDetectorImplementation.parseLine("a,b," + SIGNIN_FAILURE + ",d")).as("shouldreturnNullBadIp").isNull();
	}

	@Test
	public void shouldreturnNullBadEPOCH() {
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenThrow(new NumberFormatException());
		assertThat(hackerDetectorImplementation.parseLine(LINE_BAD_EPOCH)).as("shouldreturnNullBadEPOCH").isNull();
	}

	@Test
	public void shouldreturnNullLoginOk() {
		assertThat(hackerDetectorImplementation.parseLine(LINE_OK_LOGIN)).as("shouldreturnNullLoginOk").isNull();
	}
	
	@Test
	public void shouldreturnNullLoginFail() {
		assertThat(hackerDetectorImplementation.parseLine(LINE_LOGIN_FAIL)).as("shouldreturnNullLoginFail").isNull();
	}
	
	@Test
	public void shouldreturnLine() {
		assertThat(hackerDetectorImplementation.parseLine(LINE_OK)).as("shouldreturnLine").isEqualTo(LINE_OK);
	}
	
		
}
