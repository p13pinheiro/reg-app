package com.app.reg.springbootregapp.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.config.error.exception.ParticipanteEventoException;
import com.app.reg.springbootregapp.dominio.Usuario;
import com.app.reg.springbootregapp.form.ParticipanteForm;
import com.app.reg.springbootregapp.repository.UsuarioRepository;

@Service
public class UsuarioExistenteValidator extends ParticipanteValidator{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioExistenteValidator(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	public void validar(List<ParticipanteForm> participantesForm) throws Exception {
		
		if(participantesForm != null) {
			List<String> notFoundEmail = new ArrayList<String>();
			
			participantesForm.forEach(form -> {
				Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(form.getEmail());
				if(!usuarioOpt.isPresent()) {
					notFoundEmail.add(form.getEmail());
				}
			});
			
			if(notFoundEmail.size() > 0 ) {
				throw new ParticipanteEventoException(notFoundEmail,"Os participantes não foram encontrados para os usuários: ");
			}
		}
		proximoPasso(participantesForm);
	}
}
