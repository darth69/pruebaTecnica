package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl.UtilsDateTimeImpl;

@ExtendWith(MockitoExtension.class)
public class UtilsDateTimeImplTest {

	@InjectMocks
	UtilsDateTimeImpl utilsDateTimeImpl;
	
	@Test
	public void testParseLocalDateTimeEvent() {
		
		Instant now =  Instant.now();
		Long nowEpoch = now.toEpochMilli();
		
		LocalDateTime expected = Instant.ofEpochMilli(nowEpoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		assertThat(utilsDateTimeImpl.parseLocalDateTimeEvent(nowEpoch.toString())).isEqualTo(expected);
		
	}
	
	@Test
	public void sholudOrderLocalDateTimeList() {
		
		LocalDateTime min = LocalDateTime.now();
		LocalDateTime max = min.plusDays(1L);
		
		List<LocalDateTime> unsorted = new ArrayList<>();
		unsorted.add(min);
		unsorted.add(max);

		List<LocalDateTime> sorted = new ArrayList<>();
		sorted.add(max);
		sorted.add(min);

		assertThat(utilsDateTimeImpl.orderLocalDateTimeList(unsorted))
				.as("sholudOrderLocalDateTimeList")
				.isEqualTo(sorted);
		
	}

}
