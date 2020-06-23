package com.app.reg.springbootregapp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.app.reg.springbootregapp.dominio.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalheEventoDTO {

	private String id;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime data;
	private String local;
	private List<ExibicaoParticipanteDTO> participantes = new ArrayList<ExibicaoParticipanteDTO>();
	private List<ExibicaoProdutoDTO> produtos = new ArrayList<ExibicaoProdutoDTO>();
	private Double valorTotal;
	private Double valorPorParticipante;
	
	public DetalheEventoDTO() {}

	public DetalheEventoDTO(Evento evento) {
		List<ExibicaoParticipanteDTO> participantes = null;
		if(evento.getParticipantes() != null && !evento.getParticipantes().isEmpty()) {
			participantes = evento.getParticipantes().stream().map(ExibicaoParticipanteDTO::new).collect(Collectors.toList());
		}
		List<ExibicaoProdutoDTO> produtos = null;
		if(evento.getProdutos() != null && !evento.getProdutos().isEmpty()) {
			produtos = evento.getProdutos().stream().map(ExibicaoProdutoDTO::new).collect(Collectors.toList());
		}
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.data = evento.getData();
		this.local = evento.getLocal();
		this.participantes = participantes;
		this.produtos = produtos;
		this.valorTotal = evento.getValorTotal();
		this.valorPorParticipante = evento.getValorPorParticipante(evento.getValorTotal());
		
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getLocal() {
		return local;
	}

	public List<ExibicaoParticipanteDTO> getParticipantes() {
		return participantes;
	}

	public List<ExibicaoProdutoDTO> getProdutos() {
		return produtos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public double getValorPorParticipante() {
		return valorPorParticipante;
	}

}
