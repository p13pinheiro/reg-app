package com.app.reg.springbootregapp.dto;

import com.app.reg.springbootregapp.dominio.Produto;

public class DetalheProdutoDTO {
	
	private String nome;
	private Double preco;
	private Integer quantidade;
	
	public DetalheProdutoDTO(Produto produto) {
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
