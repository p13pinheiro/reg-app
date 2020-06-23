package com.app.reg.springbootregapp.form;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoForm {

	@NotNull @Length(min = 2)
	private String nome;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime data;
	@NotNull
	private String local;
	private AdicaoParticipanteForm adicaoParticipanteForm;
	private List<ProdutoForm> produtosForm;
	
//	public Evento converter(ParticipantePassosValidator participanteValidator) throws Exception {
//		List<Participante> participantes = null;
//		List<Produto> produtos = null;
//		//adicao de participantes nao usa validacao 1
//		participantes = adicaoParticipanteForm.converter(null,participanteValidator);
//		
//		if(produtosForm != null && !produtosForm.isEmpty()) {
//			produtos = produtosForm.stream().map(prod -> prod.converter()).collect(Collectors.toList());
//		}
//		
//		return new Evento(nome, data, local, participantes, produtos);
//	}
	
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
	public List<ProdutoForm> getProdutosForm() {
		return produtosForm;
	}

	public AdicaoParticipanteForm getAdicaoParticipanteForm() {
		return adicaoParticipanteForm;
	}

}
