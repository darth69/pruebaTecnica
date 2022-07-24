package com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.impl;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.infrastructure.utils.datetime.UtilsDateTime;

@Service
public class UtilsDateTimeImpl implements UtilsDateTime , Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605101239770221545L;
	
	
	@Override
	public LocalDateTime parseLocalDateTimeEvent(String campo) {
		//Pasamos el string a Long
		Long epoch = Long.parseLong(campo);
		
		//Devolvemos el epoch aplicando el timezone del sistema
		return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@Override
	public List<LocalDateTime> orderLocalDateTimeList(List<LocalDateTime> events) {
		//Generamos comparador para ordenar los eventos
		Comparator<LocalDateTime> comparator = (v1, v2) ->{
			return v1.compareTo(v2);
		};
		
		//Devolvemos el listado de eventos ordenados
		return events.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

	@Override
	public Long localDateTimeToEpoch(LocalDateTime ldt) {
		//Comvertimos LocalDateTime to Instant en la zona horaria del sistema
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();	
		//Devolvemos el Epoch
		return instant.toEpochMilli(); 
	}
	
	@Override
	public Long diffBetweenDateTimes(String start, String end) {
		//Recuperamos los ZonedDateTime desde los strings
		ZonedDateTime zdtStart = ZonedDateTime.parse(start ,DateTimeFormatter.RFC_1123_DATE_TIME);
		ZonedDateTime zdtEnd = ZonedDateTime.parse(end ,DateTimeFormatter.RFC_1123_DATE_TIME);
		
		//Recuperamos la diferencia en segundos entre las fechas
		Double segundos = Double.valueOf(ChronoUnit.SECONDS.between(zdtStart, zdtEnd));
		
		//Calculamos los minutos dibidiendo por 60
		Double minutos = segundos / 60;
		
		//Redondeamos los minutos y los devolvemos
		return Math.round(minutos);
				
	}

}
