package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UtilsDateTimeImplTest {

	private static final long CIENTO_VENTIUNO = 121L;
	private static final long CIENTO_VEINTE = 120L;
	@InjectMocks
	UtilsDateTimeImpl utilsDateTimeImpl;
	
	@Test
	void testParseLocalDateTimeEvent() {
		
		Instant now =  Instant.now();
		Long nowEpoch = now.toEpochMilli();
		
		LocalDateTime expected = Instant.ofEpochMilli(nowEpoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		assertThat(utilsDateTimeImpl.parseLocalDateTimeEvent(nowEpoch.toString())).isEqualTo(expected);
		
	}
	
	@Test
	void sholudOrderLocalDateTimeList() {
		
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
	
	@Test
	void shouldReturn120() {
		Long res = utilsDateTimeImpl.diffBetweenDateTimes("Thu, 21 Dec 2000 16:01:00 +0400", "Thu, 21 Dec 2000 16:01:29 +0200");
		assertThat(res).isEqualTo(CIENTO_VEINTE);
	}
	
	@Test
	void shouldReturn121() {
		Long res = utilsDateTimeImpl.diffBetweenDateTimes("Thu, 21 Dec 2000 16:01:00 +0400", "Thu, 21 Dec 2000 16:01:30 +0200");
		assertThat(res).isEqualTo(CIENTO_VENTIUNO);
	}

}
