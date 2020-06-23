package com.app.reg.springbootregapp.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EdicaoEventoForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime data;
	
	@NotNull @NotEmpty
	private String local;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
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
