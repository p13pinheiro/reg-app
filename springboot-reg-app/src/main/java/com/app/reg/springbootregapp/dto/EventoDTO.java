package com.app.reg.springbootregapp.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.app.reg.springbootregapp.dominio.Evento;

public class EventoDTO {
	
	private Long id;
	private String nome;
	private Date data;
	private String local;
	
	public EventoDTO(Evento evento) {
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.data = evento.getData();
		this.local = evento.getLocal();
	}

	public static Page<EventoDTO> converter(Page<Evento> eventos){
		return eventos.map(EventoDTO::new);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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

}
