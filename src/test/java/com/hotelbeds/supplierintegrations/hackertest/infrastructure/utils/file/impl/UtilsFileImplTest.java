package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.file.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UtilsFileImplTest {

	@InjectMocks
	UtilsFileImpl utilsFileImpl;
	
	@TempDir
	private static File tempDir;	
	
	private static String testDir;
	private static String baseTestDir;
	
	@BeforeAll
	private static void beforeAllSetup() {
		baseTestDir = tempDir.getAbsolutePath(); 
		testDir =  baseTestDir + "/test";
	}
	
	@AfterAll
	public static void afterAllClean() {
		File dirTest = new File(testDir);		
		dirTest.delete();
		
		File baseDirTest = new File(baseTestDir);
		baseDirTest.delete();
	}
	
	@Test
	void testCrearDirectoriosNoExiste() {
		
		utilsFileImpl.CrearDirectorios(testDir);
		
		File dirTest = new File(testDir);
		assertThat(dirTest).as("testCrearDirectoriosNoExiste").exists();
		dirTest.delete();
	}
	
	@Test
	void testCrearDirectoriosExiste() {
				
		utilsFileImpl.CrearDirectorios(baseTestDir);
				
		assertThat(tempDir).as("testCrearDirectoriosExiste").exists();
	}
}
