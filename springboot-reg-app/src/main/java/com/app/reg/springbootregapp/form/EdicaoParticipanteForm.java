package com.app.reg.springbootregapp.form;

import com.app.reg.springbootregapp.dominio.Participante;

public class EdicaoParticipanteForm {

	private String nome;
	private String endereco;
	
	public EdicaoParticipanteForm(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public EdicaoParticipanteForm(Participante participante) {
		this.nome = participante.getNome();
//		this.endereco = participante.getEndereco();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Participante editar(Participante participante) {
		participante.setNome(this.nome);
//		participante.setEndereco(this.endereco);
		
		return participante;
	}
	
}
