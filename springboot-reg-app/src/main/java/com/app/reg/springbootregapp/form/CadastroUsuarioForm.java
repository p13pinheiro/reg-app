package com.app.reg.springbootregapp.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.reg.springbootregapp.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CadastroUsuarioForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	@NotNull
	private Integer idade;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull 	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
	@NotNull @NotEmpty
	private String cpf;
	
	public Usuario converter(PasswordEncoder passwordEncoder) {
		return new Usuario(this.nome, this.email, passwordEncoder.encode(this.senha), this.idade, this.endereco, this.dataNascimento, this.cpf);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
