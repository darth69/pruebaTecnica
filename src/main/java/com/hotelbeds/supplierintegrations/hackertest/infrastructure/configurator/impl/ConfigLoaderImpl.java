package com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;

@Service
@Scope("singleton")
public class ConfigLoaderImpl implements ConfigLoader{

	@Value("${logger.type}")
	private String loggerType;
	
	@Value("${detector.type}")	
	private String detectorType;
	
	@Value("${detector.ip.storage.path}")
	private String rutaAlmacenIps;
	
	@Value("${detector.ip.max.limit}")
	private long maxLimit;

	@Value("${detector.ip.min.limit}")
	private long minLimit;

	@Value("${detector.ip.retry.limit}")
	private long retryLimit;

	@Override
	public String getLoggerType() {
		return loggerType;
	}

	@Override
	public String getDetectorType() {
		return detectorType;
	}
	
	@Override
	public String getRutaAlmacenIps() {
		return rutaAlmacenIps;
	}
	
	@Override
	public Long getMaxLimit() {
		return maxLimit;
	}
	
	@Override
	public Long getMinLimit() {
		return minLimit;
	}
	
	@Override
	public Long getRetryLimit() {
		return retryLimit;
	}
	
	
}
