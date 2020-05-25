package com.app.reg.springbootregapp.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Produto;

public class AdicaoProdutosForm {
	
	@NotNull @NotEmpty
	private List<ProdutoForm> produtosForm;

	public List<Produto> converter(){
		return produtosForm.stream().map(p -> p.converter()).collect(Collectors.toList());
	}
	public List<ProdutoForm> getProdutosForm() {
		return produtosForm;
	}

}
