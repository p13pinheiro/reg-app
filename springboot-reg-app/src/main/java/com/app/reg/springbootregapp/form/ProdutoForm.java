package com.app.reg.springbootregapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.app.reg.springbootregapp.dominio.Produto;

public class ProdutoForm {

	@NotNull @NotEmpty @Length(min = 1, max = 30)
	private String nome;
	@NotNull @Min(1)
	private Double preco;
	@NotNull
	private Integer quantidade;
	
	public String getNome() {
		return nome;
	}
	public Double getPreco() {
		return preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public Produto converter() {
		return new Produto(nome, preco, quantidade);
	}
	
}
