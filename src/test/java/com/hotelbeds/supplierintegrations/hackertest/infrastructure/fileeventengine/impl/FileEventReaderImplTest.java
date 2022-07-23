package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl.FileEventReaderImpl;

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
