package com.app.reg.springbootregapp.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Evento;

public class EdicaoEventoForm {

	@NotNull
	private String nome;
	
	@NotNull
	private Date data;
	
	@NotNull
	private String local;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Evento editar(Evento evento) {
		evento.setData(this.data);
		evento.setLocal(this.local);
		evento.setNome(this.nome);
		return evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
