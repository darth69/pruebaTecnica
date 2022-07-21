package com.hotelbeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;

@Component
public class Runner implements CommandLineRunner{

	@Autowired
	private HackerDetector hackerDetector;
	
	@Override
	public void run(String... args) throws Exception {
		hackerDetector.parseLine(null);				
	}

}
