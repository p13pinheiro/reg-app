package com.app.reg.springbootregapp.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.ParticipanteForm;
import com.app.reg.springbootregapp.ifacade.IParticipanteFacade;
import com.app.reg.springbootregapp.servicos.ParticipanteSV;

@Service
public class ParticipanteFacade implements IParticipanteFacade {

	private static final long serialVersionUID = 4858936188856232758L;

	@Autowired
	private ParticipanteSV participanteSV;
	
	@Override
	public List<Participante> validar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception {
		return participanteSV.validar(participantesForm, evento);
	}

	@Override
	public Evento adicionar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception {
		return participanteSV.adicionar(participantesForm, evento);
	}

}
