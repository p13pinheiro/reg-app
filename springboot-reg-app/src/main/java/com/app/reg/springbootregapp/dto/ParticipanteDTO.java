package com.app.reg.springbootregapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.app.reg.springbootregapp.dominio.Participante;

public class ParticipanteDTO {
	
	private Long id;
	private String nome;
	private Integer idade;
	private String endereco;
	
	public ParticipanteDTO(Participante participante) {
		this.id = participante.getId();
		this.nome = participante.getNome();
		this.idade = participante.getIdade();
		this.endereco = participante.getEndereco();
	}
	
	public static List<ParticipanteDTO> converter(List<Participante> participantes){
		return participantes.stream().map(ParticipanteDTO::new).collect(Collectors.toList());
	}
	
	public void setId(Long id) {
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

	public Long getId() {
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

}
