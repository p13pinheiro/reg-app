package com.app.reg.springbootregapp.config.error.dto;

public class ErrorFormularioDTO {

	private String nomeCampo;
	private String descricaoErro;

	public ErrorFormularioDTO(String nomeCampo, String descricaoErro) {
		this.nomeCampo = nomeCampo;
		this.descricaoErro = descricaoErro;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

}
