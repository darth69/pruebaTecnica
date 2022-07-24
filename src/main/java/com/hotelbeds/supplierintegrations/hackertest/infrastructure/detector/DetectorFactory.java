package com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

public interface DetectorFactory extends Serializable {
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime);
}
