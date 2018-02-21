package com.sprinApi.dto;

import java.io.Serializable;

public class CredenciasDTO implements Serializable {

	private static final long serialVersionUID = 2012614295315645481L;
	private String email;
	private String senha;

	public CredenciasDTO() {

	}

	public CredenciasDTO(String email, String senha) {
		super();
		this.setEmail(email);
		this.setSenha(senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
