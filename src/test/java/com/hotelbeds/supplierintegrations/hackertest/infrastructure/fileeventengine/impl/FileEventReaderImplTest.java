package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl.FileEventReaderImpl;

@ExtendWith(MockitoExtension.class)
class FileEventReaderImplTest {
	
	@InjectMocks
	FileEventReaderImpl fileEventReaderImpl;

	@Test
	public void testRecoveryEventsForIp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("files/192.168.1.0.txt").getFile());
		
		assertThat(file).exists();
		assertThat(fileEventReaderImpl.recoveryEventsForIp(file)).hasAtLeastOneElementOfType(String.class);
	}

}
