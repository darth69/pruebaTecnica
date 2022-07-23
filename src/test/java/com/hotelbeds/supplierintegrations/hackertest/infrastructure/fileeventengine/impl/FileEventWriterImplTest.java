package com.hotelbeds.supplierintegrations.hackertest.infrastructure.fileeventengine.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.configurator.ConfigLoader;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@ExtendWith(MockitoExtension.class)
class FileEventWriterImplTest {
	
	private static final long CINCO_LONG = 5L;

	private static final String FILES_192_168_1_1_TXT = "files/192.168.1.1.txt";

	@InjectMocks
	private FileEventWriterImpl fileEventWriterImpl;
	
	@Mock
	private UtilsDateTime utilsDateTime;
	
	@Mock
	private ConfigLoader configLoader;

	@Test
	void testWriteEvents() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILES_192_168_1_1_TXT).getFile());
		
		when(configLoader.getRetryLimit()).thenReturn(CINCO_LONG);
		
		assertThat(file).exists();
		
		List<LocalDateTime> events = new ArrayList<>();
		
		for(int i = 0;i<6;i++) {
			events.add(LocalDateTime.now().plusSeconds(i));
		}
		
		boolean exception = false;
		
		try {
			fileEventWriterImpl.writeEvents(file, events);
		} catch (IOException e) {
			exception = true;
		}
		
		assertThat(exception).isFalse();
	}

}
