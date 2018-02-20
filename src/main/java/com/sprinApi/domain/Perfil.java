package com.sprinApi.domain;

public enum Perfil {

	ADM(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int codigo;
	private String descricao;

	private Perfil(int codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Perfil tipo : Perfil.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido :" + codigo);
	}

}
