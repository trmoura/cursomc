package com.sprinApi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sprinApi.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = -3983882992760037976L;

	private Integer id;

	private String nome;

	private BigDecimal preco;

	public ProdutoDTO() {

	}

	public ProdutoDTO (Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
