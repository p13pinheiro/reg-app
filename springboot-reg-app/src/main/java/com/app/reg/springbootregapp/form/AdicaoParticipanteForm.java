package com.app.reg.springbootregapp.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.reg.springbootregapp.dominio.Participante;

public class AdicaoParticipanteForm {

	@NotNull @NotEmpty
	private List<ParticipanteForm> participantesForm;

	public List<ParticipanteForm> getParticipantesForm() {
		return participantesForm;
	}

	public List<Participante> converter() {
		return participantesForm.stream().map(form -> form.converter()).collect(Collectors.toList());
	}

}
