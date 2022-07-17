package com.hotelbeds.supplierintegrations.hackertest.application.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UtilsDateTime {
	
	public static LocalDateTime parseLocalDateTimeEvent(String campo) {
		Long epoch = Long.parseLong(campo);
		
		return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
