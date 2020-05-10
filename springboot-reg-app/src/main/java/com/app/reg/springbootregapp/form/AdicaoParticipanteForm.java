package com.app.reg.springbootregapp.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdicaoParticipanteForm {

	@NotNull @NotEmpty
	private List<Long> participanteIds;

	public List<Long> getParticipanteIds() {
		return participanteIds;
	}


}
