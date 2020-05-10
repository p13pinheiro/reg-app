package com.app.reg.springbootregapp.dto;

import com.app.reg.springbootregapp.dominio.Participante;

public class DetalheParticipanteDTO {
	
	private Long id;
	private String nome;
	private Integer idade;
	private String endereco;
	
	public DetalheParticipanteDTO(Participante participante) {
		this.nome = participante.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}

}
