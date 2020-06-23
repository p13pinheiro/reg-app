package com.app.reg.springbootregapp.dto;

import com.app.reg.springbootregapp.dominio.Produto;

public class ExibicaoProdutoDTO {

	private String nome;
	private Double preco;
	private Integer quantidade;
	
	public ExibicaoProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.quantidade = produto.getQuantidade();
	}
	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
}
