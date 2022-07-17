package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

public class DetectorFromFile implements DetectorFactory {

	@Value("")
	public String rutaAlmacenIps;
	
	@Override
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime) {
		
		return false;
	}
}