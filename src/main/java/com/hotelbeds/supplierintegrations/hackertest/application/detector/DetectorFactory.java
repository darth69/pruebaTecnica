package com.hotelbeds.supplierintegrations.hackertest.application.detector;

import java.time.LocalDateTime;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

public interface DetectorFactory {
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime);
}
