package com.tulian.springboot.apirest.app.models.entity;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private String texto;
	private Long fecha;
	private String username;
	private String tipo;
	private String color;
	
	
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
