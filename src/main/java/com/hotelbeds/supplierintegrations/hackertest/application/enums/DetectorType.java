package com.hotelbeds.supplierintegrations.hackertest.application.enums;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.detector.DetectorFactory;


// Enumerador data los tipos de Detectores del Factory
public enum DetectorType {
	
	DETECTORFROMFILE(){		
		private DetectorFactory detectorFactory;
		
		@Override
		public DetectorFactory getDetector() {
			return detectorFactory;
		}

		@Override
		public void setDetector(DetectorFactory detectorFactory) {
			this.detectorFactory = detectorFactory;			
		}
	};	
	
	public abstract DetectorFactory getDetector();
	public abstract void setDetector(DetectorFactory detectorFactory);

}