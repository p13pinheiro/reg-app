package com.app.reg.springbootregapp.dto;

import java.time.LocalDate;

import com.app.reg.springbootregapp.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CadastroUsuarioDTO {

	private String id;
	private String nome;
	private String email;
	private String senha;
	private Integer idade;
	private String endereco;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDate dataNascimento;
	private String cpf;
	
	public CadastroUsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.idade = usuario.getIdade();
		this.endereco = usuario.getEndereco();
		this.dataNascimento = usuario.getDataNascimento();
		this.cpf = usuario.getCpf();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

}
