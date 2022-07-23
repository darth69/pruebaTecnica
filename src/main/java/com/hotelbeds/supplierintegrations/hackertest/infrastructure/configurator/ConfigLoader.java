package com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator;

public interface ConfigLoader {

	String getLoggerType();

	String getDetectorType();

	String getRutaAlmacenIps();

	Long getMaxLimit();

	Long getMinLimit();

	Long getRetryLimit();

}
