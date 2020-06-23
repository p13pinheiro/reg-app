package com.app.reg.springbootregapp.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dominio.Usuario;
import com.app.reg.springbootregapp.repository.UsuarioRepository;

public class ParticipanteForm {

	@NotNull
	@NotEmpty
	@Length(min = 3, max=50)
	private String email;

	public ParticipanteForm() {}
	public ParticipanteForm(String email) {
		this.email = email;
	}
	public Participante converter(UsuarioRepository usuarioRepository) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(this.email);
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			return new Participante(new ObjectId(usuario.getId()), usuario.getNome(), email);
		}
		return null;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail() {
		return email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		ParticipanteForm other = (ParticipanteForm) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
