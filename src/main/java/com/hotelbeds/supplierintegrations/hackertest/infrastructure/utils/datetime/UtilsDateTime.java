package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface UtilsDateTime extends Serializable{

	LocalDateTime parseLocalDateTimeEvent(String campo);
	
	List<LocalDateTime> orderLocalDateTimeList(List<LocalDateTime> events);
	
	Long localDateTimeToEpoch(LocalDateTime ldt);

}
