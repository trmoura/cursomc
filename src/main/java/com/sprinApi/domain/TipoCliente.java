package com.sprinApi.domain;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public static TipoCliente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (TipoCliente tipo : TipoCliente.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido :" + codigo);
	}

}
