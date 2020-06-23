package com.app.reg.springbootregapp.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.form.EventoForm;
import com.app.reg.springbootregapp.ifacade.IEventoFacade;
import com.app.reg.springbootregapp.servicos.EventoSV;
import com.app.reg.springbootregapp.vo.ValorAgredadoEvento;

@Service
public class EventoFacade implements IEventoFacade {

	private static final long serialVersionUID = -6940166685446117297L;

	@Autowired
	private EventoSV eventoSV;
	
	@Override
	public ValorAgredadoEvento calcularValorAgredado(Evento evento) {
		return eventoSV.calcularValorAgredado(evento);
	}

	@Override
	public Evento inserirDadosEvento(EventoForm eventoForm) throws Exception {
		return eventoSV.inserirDadosEvento(eventoForm);
	}

}
