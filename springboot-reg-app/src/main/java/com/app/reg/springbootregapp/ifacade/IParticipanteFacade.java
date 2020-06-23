package com.app.reg.springbootregapp.ifacade;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.ParticipanteForm;

@Service
public interface IParticipanteFacade extends Serializable{

	public List<Participante> validar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception;
	public Evento adicionar(List<ParticipanteForm> participantesForm, Evento evento) throws Exception;
}
