package com.app.reg.springbootregapp.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dominio.Produto;
import com.app.reg.springbootregapp.repository.ParticipanteRepository;
import com.app.reg.springbootregapp.repository.ProdutoRepository;

public class EventoForm {

	@NotNull @Length(min = 2)
	private String nome;
	@NotNull
	private Date data;
	@NotNull
	private String local;
	private List<Long> participanteIds;
	private List<Long> produtoIds;
	
	public Evento converter(ParticipanteRepository participanteRepository, ProdutoRepository produtoRepository) {
		List<Participante> participantes = new ArrayList<Participante>();
		List<Produto> produtos = new ArrayList<Produto>();
		if(participanteIds != null && !participanteIds.isEmpty()) {
			participanteIds.forEach(p -> {
				Optional<Participante> optional = participanteRepository.findById(p);
				if(optional.isPresent()) {
					participantes.add(optional.get());
				}
			}
			);
		}
		if(produtoIds != null & !produtoIds.isEmpty()) {
			produtoIds.forEach(p -> {
				Optional<Produto> optional = produtoRepository.findById(p);
				if(optional.isPresent()) {
					produtos.add(optional.get());
				}
			});
		}
		return new Evento(nome, data, local, participantes, produtos);
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
	public List<Long> getParticipanteIds() {
		return participanteIds;
	}
	public List<Long> getProdutoIds() {
		return produtoIds;
	}

}
