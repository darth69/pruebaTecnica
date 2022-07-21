package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@Service
public class UtilsDateTimeImpl implements UtilsDateTime {
	
	@Override
	public LocalDateTime parseLocalDateTimeEvent(String campo) {
		Long epoch = Long.parseLong(campo);
		
		return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@Override
	public List<LocalDateTime> orderLocalDateTimeList(List<LocalDateTime> events) {
		
		Comparator<LocalDateTime> comparator = (v1, v2) ->{
			return v1.compareTo(v2);
		};
		
		return events.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

	@Override
	public Long LocalDateTimeToEpoch(LocalDateTime ldt) {
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();	
		return instant.toEpochMilli(); 
	}

}
