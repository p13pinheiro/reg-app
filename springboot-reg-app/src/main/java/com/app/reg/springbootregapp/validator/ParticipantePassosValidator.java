package com.app.reg.springbootregapp.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.ParticipanteForm;
import com.app.reg.springbootregapp.repository.UsuarioRepository;

@Service
public class ParticipantePassosValidator {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Participante> validarPassosCompleto(List<ParticipanteForm> participantesForm, Evento evento) throws Exception {
		if(participantesForm != null && !participantesForm.isEmpty()) {

			ParticipanteValidator validarDuplicidade = new ParticipanteDuplicadoValidator(evento);
			validarDuplicidade.setProximoValidator(new UsuarioExistenteValidator(usuarioRepository));
			
			validarDuplicidade.validar(participantesForm);
	
			return participantesForm.stream().distinct().map(form -> form.converter(usuarioRepository)).collect(Collectors.toList());
		}
		return null;
	}
}
