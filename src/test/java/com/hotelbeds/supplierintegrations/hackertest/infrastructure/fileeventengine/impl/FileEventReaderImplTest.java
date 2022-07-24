package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;

@ExtendWith(MockitoExtension.class)
class FileEventReaderImplTest {
	
	@InjectMocks
	FileEventReaderImpl fileEventReaderImpl;
	
	@Mock
	private ConfigLoader configLoader;

	@Test
	void testRecoveryEventsForIp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("files/192.168.1.0.txt").getFile());
		
		when(configLoader.getLoggerType()).thenReturn("LOGGER2LOG");
		
		assertThat(file).exists();
		assertThat(fileEventReaderImpl.recoveryEventsForIp(file)).hasAtLeastOneElementOfType(String.class);
	}

	@Test
	void testFileNotExists() {		
		File file = new File("dummy");
		file.deleteOnExit();

		assertThat(file).doesNotExist();
		assertThat(fileEventReaderImpl.recoveryEventsForIp(file)).isEmpty();
	}
}
