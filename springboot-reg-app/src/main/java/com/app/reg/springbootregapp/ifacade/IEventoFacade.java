package com.app.reg.springbootregapp.ifacade;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.form.EventoForm;
import com.app.reg.springbootregapp.vo.ValorAgredadoEvento;

@Service
public interface IEventoFacade extends Serializable{
	public ValorAgredadoEvento calcularValorAgredado(Evento evento);
//	public DetalheEventoDTO preencherCamposEvento(Evento evento);
	
	public Evento inserirDadosEvento(EventoForm eventoForm)throws Exception ;
}
