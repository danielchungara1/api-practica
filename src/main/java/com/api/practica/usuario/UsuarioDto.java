package com.api.practica.usuario;

import com.api.practica.commons.dto.BaseDto;


public class UsuarioDto extends BaseDto {
	
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
