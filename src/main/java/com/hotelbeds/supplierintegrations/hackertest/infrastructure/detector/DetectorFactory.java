package com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hotelbeds.supplierintegrations.hackertest.model.Ip;

//Factgory par la carga de Detectores
public interface DetectorFactory extends Serializable {
	public boolean analizeIp(Ip ip, LocalDateTime eventDateTime);
}
