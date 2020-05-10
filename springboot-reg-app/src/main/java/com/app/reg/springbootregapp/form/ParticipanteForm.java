package com.app.reg.springbootregapp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.app.reg.springbootregapp.dominio.Participante;

public class ParticipanteForm {

	@NotNull
	@NotEmpty
	@Length(min = 3, max=50)
	private String nome;
	private Integer idade;
	private String endereco;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Participante converter() {
		return new Participante(nome, idade,endereco);
	}

}
