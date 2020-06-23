package com.app.reg.springbootregapp.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdicaoParticipanteForm {
	
	@NotNull @NotEmpty
	private List<ParticipanteForm> participantesForm;

//	public List<Participante> converter(Evento evento,ParticipantePassosValidator participanteValidator) throws Exception {
//		return participanteValidator.validarPassosCompleto(participantesForm, evento);
//	}
	
	public List<ParticipanteForm> getParticipantesForm() {
		return participantesForm;
	}
}
