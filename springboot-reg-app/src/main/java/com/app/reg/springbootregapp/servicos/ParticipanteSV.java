package com.app.reg.springbootregapp.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.ParticipanteForm;
import com.app.reg.springbootregapp.repository.EventoRepository;
import com.app.reg.springbootregapp.validator.ParticipantePassosValidator;

@Service
public class ParticipanteSV implements SV {

	private static final long serialVersionUID = 6623152527910556084L;

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ParticipantePassosValidator participantePassosValidator;
	
	/**
	 * Valida os passos para os participantes que estão sendo adiconados ao evento
	 * @param participantesForm
	 * @param evento
	 * @return
	 * @throws Exception
	 */
	public List<Participante> validar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception {
		return participantePassosValidator.validarPassosCompleto(participantesForm, evento);
	}
	
	public Evento adicionar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception {
		List<Participante> participantes = participantePassosValidator.validarPassosCompleto(participantesForm, evento);
		
		if(evento.getParticipantes() != null) {
			evento.getParticipantes().addAll(participantes);
		}else {
			evento.setParticipantes(participantes);
		}
		
		return eventoRepository.save(evento);
	}
}
