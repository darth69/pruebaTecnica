package com.hotelbeds.supplierintegrations.hackertest.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IpTest {

	private static final String IP = "192.168.1.1";

	@Test
	public void shouldFailIpNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Ip ip = new Ip(null);
	    });
		
		assertThat(exception.getMessage()).as("shouldFailIpNull").contains("IP nula detectada");
	}
	
	@Test
	public void shouldFailIpInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Ip ip = new Ip("192.168");
	    });
		
		assertThat(exception.getMessage()).as("shouldFailIpInvalid").contains("Formato incorrecto para la IP 192.168");
	}

	@Test
	public void shouldFailIpInvalidOcteto() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Ip ip = new Ip("192.168.0.255");
		});
		
		assertThat(exception.getMessage()).as("shouldFailIpInvalidOcteto").contains("Formato incorrecto para el octeto con valor 255");
	}
	
	@Test
	public void shouldReturnIp() {
		Ip ip = new Ip(IP);
		
		assertThat(ip.getIp()).as("shouldReturnIp").isEqualTo(IP);
	}
}
