package com.app.reg.springbootregapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Produto;

public class EdicaoProdutoForm {

	@NotNull @Min(1)
	private Double preco;
	@NotNull
	private Integer quantidade;
	
	public Double getPreco() {
		return preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void editar(Produto produto) {
		produto.setPreco(this.preco);
		produto.setQuantidade(this.quantidade);
	}
	
}
