package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector.DetectorFromFile;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventReader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.FileEventWriter;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.UtilsFile;
import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

@ExtendWith(MockitoExtension.class)
public class DetectorFromFileTest {
	
	private static final long LONG_300PLUS = 300L; 
	private static final long LONG_300MINUS = -300L;
	private static final long LONG_301PLUS = 301L; 
	private static final long LONG_301MINUS = -301L; 

	private static final String IP_192_168_1_0 = "192.168.1.0";

	@Value("${detector.ip.storage.path}")
	private String rutaAlmacenIps;
	
	@InjectMocks
	DetectorFromFile detectorFromFile;
	
	@Mock
	FileEventReader fileEventReader;

	@Mock
	UtilsDateTime utilsDateTime;
	
	@Mock
	UtilsFile utilsFile;
	
	@Mock
	FileEventWriter fileEventWriter;
	
	@BeforeEach
	private void initTest() {
		ReflectionTestUtils.setField(detectorFromFile, "rutaAlmacenIps", "/ips");
		ReflectionTestUtils.setField(detectorFromFile, "maxLimit", 300L);
		ReflectionTestUtils.setField(detectorFromFile, "minLimit", -300L);
		ReflectionTestUtils.setField(detectorFromFile, "retryLimit", 1L);
	}
	
	@Test
	public void shouldTrue300Plus() {
		
		ArrayList<String> events = new ArrayList<>();
		
		LocalDateTime ahora = LocalDateTime.now();
		
		ZonedDateTime ahoraZoned5 = ahora.plusSeconds(LONG_300PLUS).atZone(ZoneId.systemDefault());
		Long now5 = ahoraZoned5.toInstant().toEpochMilli();
		events.add(now5.toString());
		
		List<LocalDateTime> ldtList = new ArrayList<>();
		ldtList.add(ahora);
		
		when(utilsDateTime.orderLocalDateTimeList(any())).thenReturn(ldtList);
		when(fileEventReader.recoveryEventsForIp(any())).thenReturn(events);
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenReturn(ahora.plusSeconds(LONG_300PLUS));
		doNothing().when(utilsFile).CrearDirectorios(any());
		
		assertThat(detectorFromFile.analizeIp(new Ip(IP_192_168_1_0), ahora)).as("shouldTrue300Plus").isTrue();
	}
	
	@Test
	public void shouldTrue300Minus() {
		
		ArrayList<String> events = new ArrayList<>();
		
		LocalDateTime ahora = LocalDateTime.now();
		
		ZonedDateTime ahoraZoned5 = ahora.plusSeconds(LONG_300MINUS).atZone(ZoneId.systemDefault());
		Long now5 = ahoraZoned5.toInstant().toEpochMilli();
		events.add(now5.toString());
		
		List<LocalDateTime> ldtList = new ArrayList<>();
		ldtList.add(ahora);
		
		when(utilsDateTime.orderLocalDateTimeList(any())).thenReturn(ldtList);
		when(fileEventReader.recoveryEventsForIp(any())).thenReturn(events);
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenReturn(ahora.plusSeconds(LONG_300MINUS));
		
		assertThat(detectorFromFile.analizeIp(new Ip(IP_192_168_1_0), ahora)).as("shouldTrue300Minus").isTrue();
	}
	
	@Test
	public void shouldFalse301Minus() {
		
		ArrayList<String> events = new ArrayList<>();
		
		LocalDateTime ahora = LocalDateTime.now();
				
		ZonedDateTime ahoraZoned5 = ahora.plusSeconds(LONG_301MINUS).atZone(ZoneId.systemDefault());
		Long now5 = ahoraZoned5.toInstant().toEpochMilli();
		events.add(now5.toString());
		
		List<LocalDateTime> ldtList = new ArrayList<>();
		ldtList.add(ahora);
		
		when(utilsDateTime.orderLocalDateTimeList(any())).thenReturn(ldtList);
		when(fileEventReader.recoveryEventsForIp(any())).thenReturn(events);
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenReturn(ahora.plusSeconds(LONG_301MINUS));
		
		assertThat(detectorFromFile.analizeIp(new Ip(IP_192_168_1_0), ahora)).as("shouldFalse301Minus").isFalse();
	}

	@Test
	public void shouldFalse301Plus() {
		
		ArrayList<String> events = new ArrayList<>();
		
		LocalDateTime ahora = LocalDateTime.now();
		
		ZonedDateTime ahoraZoned5 = ahora.plusSeconds(LONG_301PLUS).atZone(ZoneId.systemDefault());
		Long now5 = ahoraZoned5.toInstant().toEpochMilli();
		events.add(now5.toString());
		
		List<LocalDateTime> ldtList = new ArrayList<>();
		ldtList.add(ahora);
		
		when(utilsDateTime.orderLocalDateTimeList(any())).thenReturn(ldtList);
		when(fileEventReader.recoveryEventsForIp(any())).thenReturn(events);
		when(utilsDateTime.parseLocalDateTimeEvent(any())).thenReturn(ahora.plusSeconds(LONG_301PLUS));
		
		assertThat(detectorFromFile.analizeIp(new Ip(IP_192_168_1_0), ahora)).as("shouldFalse301Plus").isFalse();
	}
}
