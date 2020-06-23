package com.app.reg.springbootregapp.config.error.exception;

import java.util.List;

public class ParticipanteEventoException extends Exception {

	private static final long serialVersionUID = 2242207444590908332L;

	private final List<String> email;
	private final String mensagem;
	
	public ParticipanteEventoException(List<String> email, String mensagem) {
		this.email = email;
		this.mensagem = mensagem;
	}

	@Override
	public String getMessage() {
		StringBuilder str = new StringBuilder();
		str.append(mensagem);
		str.append(email);
		
		return str.toString();
	}

}
