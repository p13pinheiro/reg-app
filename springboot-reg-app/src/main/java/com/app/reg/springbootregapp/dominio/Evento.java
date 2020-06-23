package com.app.reg.springbootregapp.dominio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

@Service
public class Evento {
	
	public Evento() {
	}

	public Evento(String id, String nome, LocalDateTime data, String local, List<Participante> participantes, List<Produto> produtos) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.local = local;
		this.participantes = participantes;
		this.produtos = produtos;
	}
	
	public Evento(String nome, LocalDateTime data, String local) {
		this.nome = nome;
		this.data = data;
		this.local = local;
	}

	public Evento(String nome, LocalDateTime data, String local, List<Participante> participantes, List<Produto> produtos) {
		this.nome = nome;
		this.data = data;
		this.local = local;
		this.participantes = participantes;
		this.produtos = produtos;
	}


	@Id
	private String id;
	
	@Length(max = 50)
	@NotNull
	private String nome;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime data;
	
	@Length(max = 50)
	@NotNull
	private String local;
	
	@ManyToMany
	@Cascade(CascadeType.MERGE)
	private List<Participante> participantes;
	
	@ManyToMany
	@Cascade(CascadeType.MERGE)
	private List<Produto> produtos;
	
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
	public List<Participante> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Double getValorTotal() {
		if(this.getProdutos() !=  null) {
			return this.getProdutos().stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
		}
		return 0.0;
	}
	
	public Double getValorPorParticipante(Double valorTotal) {
		if(this.participantes != null && !this.participantes.isEmpty() && valorTotal != null) {
			return (double) valorTotal/this.participantes.size();
		}
		
		return 0.0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((participantes == null) ? 0 : participantes.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (participantes == null) {
			if (other.participantes != null)
				return false;
		} else if (!participantes.equals(other.participantes))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}
	
}
