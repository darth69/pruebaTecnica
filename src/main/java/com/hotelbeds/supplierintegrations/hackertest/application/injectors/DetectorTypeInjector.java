package com.hotelbeds.supplierintegrations.hackertest.application.injectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.application.enums.DetectorType;
import com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector.DetectorFromFile;

@Component    
public class DetectorTypeInjector {
   
		@Autowired        
		private DetectorFromFile detectorFromFile;        
		
		@PostConstruct        
		public void postConstruct() {            
			DetectorType.DETECTORFROMFILE.setDetector(detectorFromFile);
		}
}
