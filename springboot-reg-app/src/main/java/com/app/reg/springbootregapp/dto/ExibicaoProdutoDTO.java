package com.app.reg.springbootregapp.dto;

import com.app.reg.springbootregapp.dominio.Produto;

public class ExibicaoProdutoDTO {

	private String nome;
	private Double preco;
	
	public ExibicaoProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}
	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}
	
}
