package com.app.reg.springbootregapp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.ifacade.IEventoFacade;
import com.app.reg.springbootregapp.vo.ValorAgredadoEvento;

public class DetalheEventoDTO {

	private String id;
	private String nome;
	private Date data;
	private String local;
	private List<ExibicaoParticipanteDTO> participantes = new ArrayList<ExibicaoParticipanteDTO>();
	private List<ExibicaoProdutoDTO> produtos = new ArrayList<ExibicaoProdutoDTO>();
	private double valorTotal;
	private double valorPorParticipante;
	
	public DetalheEventoDTO() {}
	
	public DetalheEventoDTO(String id, String nome, Date data, String local, List<ExibicaoParticipanteDTO> participantes,List<ExibicaoProdutoDTO> produtos, double valorTotal, double valorPorParticipante) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.local = local;
		this.participantes = participantes;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.valorPorParticipante = valorPorParticipante;
	}
	
	public DetalheEventoDTO(Evento evento, IEventoFacade eventoFacade){
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.data = evento.getData();
		this.local = evento.getLocal();
		this.participantes.addAll(evento.getParticipantes().stream().map(ExibicaoParticipanteDTO::new).collect(Collectors.toList()));
		this.produtos.addAll(evento.getProdutos().stream().map(ExibicaoProdutoDTO::new).collect(Collectors.toList()));
		setValorCalculado(evento,eventoFacade);
	}
	
	private void setValorCalculado(Evento evento,IEventoFacade eventoFacade) {
		ValorAgredadoEvento calcularValorAgredado = eventoFacade.calcularValorAgredado(evento);
		this.valorTotal = calcularValorAgredado.getValorTotal();
		this.valorPorParticipante = calcularValorAgredado.getValorPorParticipante();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getData() {
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
