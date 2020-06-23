package com.app.reg.springbootregapp.dominio;

import org.bson.types.ObjectId;

public class Participante {
	
	private ObjectId idUsuario;
	private String nome;
	private String email;
	
	public Participante() {	}
	
	public Participante(ObjectId idUsuario, String nome, String email) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
	}
	
	public Participante(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public ObjectId getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(ObjectId idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Participante other = (Participante) obj;
		
		if (idUsuario == null) {
			if (other.idUsuario != null) {
				return false;
			}
		} else if (!idUsuario.equals(other.idUsuario)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.nome)) {
			return false;
		}
		return true;
	}

}