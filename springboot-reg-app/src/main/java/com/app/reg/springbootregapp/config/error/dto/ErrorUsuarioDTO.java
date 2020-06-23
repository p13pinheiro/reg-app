package com.app.reg.springbootregapp.config.error.dto;

public class ErrorUsuarioDTO {

	private String descricaoErro;

	public ErrorUsuarioDTO(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

}
