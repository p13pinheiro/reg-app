package com.app.reg.springbootregapp.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.app.reg.springbootregapp.dominio.Evento;

public class EdicaoEventoForm {

	@NotNull
	private Date data;
	
	@NotNull @NotEmpty
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
		return evento;
	}
}
