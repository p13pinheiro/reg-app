package com.app.reg.springbootregapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.app.reg.springbootregapp.dominio.Participante;

public class ParticipanteDTO {
	
	private String id;
	private String nome;
	private Integer idade;
	private String endereco;
	private String email;
	
	public ParticipanteDTO(Participante participante) {
		this.id = participante.getIdUsuario().toString();
		this.nome = participante.getNome();
		this.email = participante.getEmail();
	}
	
	public static List<ParticipanteDTO> converter(List<Participante> participantes){
		return participantes.stream().map(ParticipanteDTO::new).collect(Collectors.toList());
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
