package com.app.reg.springbootregapp.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdicaoProdutosForm {
	
	@NotNull @NotEmpty
	private List<Long> produtoIds;

	public List<Long> getProdutoIds() {
		return produtoIds;
	}
}
