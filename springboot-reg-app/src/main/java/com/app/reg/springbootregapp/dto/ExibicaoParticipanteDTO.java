package com.app.reg.springbootregapp.dto;

import com.app.reg.springbootregapp.dominio.Participante;

public class ExibicaoParticipanteDTO {

	private String nome;

	public ExibicaoParticipanteDTO(Participante participante) {
		this.nome = participante.getNome();
	}
	public String getNome() {
		return nome;
	}
	
}
