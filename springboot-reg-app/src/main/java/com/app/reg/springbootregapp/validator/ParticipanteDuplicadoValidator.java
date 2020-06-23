package com.app.reg.springbootregapp.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.config.error.exception.ParticipanteEventoException;
import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.ParticipanteForm;

@Service
public class ParticipanteDuplicadoValidator extends ParticipanteValidator{

	private final Evento evento;
	
	public ParticipanteDuplicadoValidator(Evento evento) {
		this.evento = evento;
	}
	/**
	 * valida se existem emails que estão sendo adicionados porém ja existem no evento
	 * também limpa os duplicados que vêm junto com a requisição (distinct)
	 */
	@Override
	public void validar(List<ParticipanteForm> participantesForm) throws Exception {
		if(participantesForm != null && evento != null) {
			List<String> emails = participantesForm.stream().map(p -> p.getEmail()).distinct().collect(Collectors.toList());
			List<String> emailsInvalidos = new ArrayList<String>();
			
			if(evento.getParticipantes() != null && !evento.getParticipantes().isEmpty()) {
				emails.forEach(m -> {
					Optional<Participante> filter = evento.getParticipantes().stream().filter(p -> p.getEmail().equals(m)).findAny();
						if(filter.isPresent()) {
							emailsInvalidos.add(filter.get().getEmail());
						}
				});
			}
			
			if(!emailsInvalidos.isEmpty()) {
				throw new ParticipanteEventoException(emailsInvalidos, "Participantes já estão inclusos no Evento.");
			}
		}
		proximoPasso(participantesForm);
	}

}
