package com.app.reg.springbootregapp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Usuario;

public class EdicaoUsuarioForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	@NotNull @NotEmpty
	private String endereco;
	
	public void editar(Usuario usuario) {
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		usuario.setEndereco(this.endereco);
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
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
