package com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator;

import java.io.Serializable;

public interface ConfigLoader extends Serializable{

	String getLoggerType();

	String getDetectorType();

	String getRutaAlmacenIps();

	Long getMaxLimit();

	Long getMinLimit();

	Long getRetryLimit();

}
