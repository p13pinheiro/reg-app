package com.app.reg.springbootregapp.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.form.EventoForm;
import com.app.reg.springbootregapp.repository.EventoRepository;
import com.app.reg.springbootregapp.vo.ValorAgredadoEvento;

@Service
public class EventoSV implements SV{
	
	private static final long serialVersionUID = -6536667430039631845L;

	@Autowired
	private ParticipanteSV participanteSV;
	
	@Autowired
	private EventoRepository eventoRepository;
	
//	public DetalheEventoDTO preencherCamposEvento(Evento evento) {
//		
//		ValorAgredadoEvento valor = calcularValorAgredado(evento);
//		List<ExibicaoParticipanteDTO> participantes = evento.getParticipantes().stream().map(ExibicaoParticipanteDTO::new).collect(Collectors.toList());
//		List<ExibicaoProdutoDTO> produtos = evento.getProdutos().stream().map(ExibicaoProdutoDTO::new).collect(Collectors.toList());
//		
//		return new DetalheEventoDTO(evento.getId(),evento.getNome(), evento.getData(), evento.getLocal(), 
//				participantes, produtos, valor.getValorTotal(), valor.getValorPorParticipante());
//		
//	}
	
	public ValorAgredadoEvento calcularValorAgredado(Evento evento) {
		
		double valortotal = calcularValorTotal(evento);
		double valorPorParticipante = cacularValorPorParticipante(evento.getParticipantes(), valortotal);
		
		return new ValorAgredadoEvento(valortotal, valorPorParticipante);		
	}

	private double cacularValorPorParticipante(List<Participante> participantes, double valortotal) {
		
		return (double) valortotal/participantes.size();
	}

	private Double calcularValorTotal(Evento evento) {
		if(evento.getProdutos() !=  null) {
			return evento.getProdutos().stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
		}
		return null;
	}

	public Evento inserirDadosEvento(EventoForm eventoForm) throws Exception {
		Evento evento = new Evento(eventoForm.getNome(), eventoForm.getData(), eventoForm.getLocal());

		if(eventoForm.getAdicaoParticipanteForm() != null) {
			List<Participante> participantes = participanteSV.validar(eventoForm.getAdicaoParticipanteForm().getParticipantesForm(), evento);
			evento.setParticipantes(participantes);
		}
		
		if(eventoForm.getProdutosForm() != null && !eventoForm.getProdutosForm().isEmpty()) {
			evento.setProdutos(eventoForm.getProdutosForm().stream().map(prod -> prod.converter()).collect(Collectors.toList()));
		}
		
		return eventoRepository.save(evento);
	}
}
