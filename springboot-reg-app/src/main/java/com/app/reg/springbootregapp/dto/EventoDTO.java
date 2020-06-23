package com.app.reg.springbootregapp.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.app.reg.springbootregapp.dominio.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoDTO {
	
	private String id;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime data;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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

}
