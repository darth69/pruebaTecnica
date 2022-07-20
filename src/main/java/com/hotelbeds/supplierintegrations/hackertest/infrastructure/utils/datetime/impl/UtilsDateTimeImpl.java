package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@Service
public class UtilsDateTimeImpl implements UtilsDateTime {
	
	@Override
	public LocalDateTime parseLocalDateTimeEvent(String campo) {
		Long epoch = Long.parseLong(campo);
		
		return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
