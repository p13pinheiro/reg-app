package com.app.reg.springbootregapp.form;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dominio.Produto;

public class EventoForm {

	@NotNull @Length(min = 2)
	private String nome;
	@NotNull
	private Date data;
	@NotNull
	private String local;
	private List<ParticipanteForm> participantesForm;
	private List<ProdutoForm> produtosForm;
	
	public Evento converter() {
		List<Participante> participantes = participantesForm.stream().map(form -> form.converter()).collect(Collectors.toList());
		List<Produto> produtos = produtosForm.stream().map(form -> form.converter()).collect(Collectors.toList());
		
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
	public List<ParticipanteForm> getParticipantesForm() {
		return participantesForm;
	}
	public List<ProdutoForm> getProdutosForm() {
		return produtosForm;
	}

}
