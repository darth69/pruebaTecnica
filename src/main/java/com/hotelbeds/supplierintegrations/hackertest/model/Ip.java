package com.hotelbeds.supplierintegrations.hackertest.model;

import java.io.Serializable;

public class Ip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3441544345424985950L;
	
	private Byte[] octeto = new Byte[4] ;
	
	public Ip(String ip) {
		setIp(ip);
	}

	public void setIp(String ip) {
		if(null == ip) {
			throw new IllegalArgumentException("IP nula detectada");
		}
		String[] octetos = ip.split("\\.");
		
		//Si no hay cuatro octetos lanzamos error
		if(octetos.length != 4) {
			throw new IllegalArgumentException("Formato incorrecto para la IP " + ip); 
		}
		
		//Rellenar los octetos de la IP
		for(int i=0;i<4;i++) {
			octeto[i] = setOcteto(octetos[i]);
		}
	}
	
	public String getIp() {
		return getOcteto(octeto[0]) + "." 
					+ getOcteto(octeto[1]) + "." 
					+ getOcteto(octeto[2]) + "." 
					+ getOcteto(octeto[3]); 
	}
	
	private Byte setOcteto(String octeto) {
		//Convertir Cadena a Numero		
		Integer octetoInt;
		try {
			octetoInt = Integer.parseInt(octeto);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("No es un numero valido para el octeto con valor " + octeto);
		}
		
		//Validar rango del octeto
		if (octetoInt <0 || octetoInt > 254) {
			throw new IllegalArgumentException("Formato incorrecto para el octeto con valor " + octeto);
		}
		return octetoInt.byteValue();
	}
	
	private String getOcteto(Byte octeto) {
		//Convertir de Byte a String valido
		Integer octetoInt = octeto & 0xFF;
		return octetoInt.toString();
	}
	
}
