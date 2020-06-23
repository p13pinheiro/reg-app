package com.app.reg.springbootregapp.validator;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.form.ParticipanteForm;

@Service
public abstract class ParticipanteValidator {

	private ParticipanteValidator proximoValidator;
	
	public abstract void validar(List<ParticipanteForm> participantesForm)throws Exception ;
	
	protected void proximoPasso(List<ParticipanteForm> participantesForm) throws Exception {
		if(proximoValidator != null) {
			proximoValidator.validar(participantesForm);
		}
	}
	
	public void setProximoValidator(ParticipanteValidator proximoValidator) {
		this.proximoValidator = proximoValidator;
	}
}
