package com.app.reg.springbootregapp.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dominio.Produto;
import com.app.reg.springbootregapp.dto.DetalheEventoDTO;
import com.app.reg.springbootregapp.dto.ExibicaoParticipanteDTO;
import com.app.reg.springbootregapp.dto.ExibicaoProdutoDTO;
import com.app.reg.springbootregapp.vo.ValorAgredadoEvento;

@Service
public class EventoSV implements SV{
	
	private static final long serialVersionUID = -6536667430039631845L;

	public DetalheEventoDTO preencherCamposEvento(Evento evento) {
		
		ValorAgredadoEvento valor = calcularValorAgredado(evento);
		List<ExibicaoParticipanteDTO> participantes = evento.getParticipantes().stream().map(ExibicaoParticipanteDTO::new).collect(Collectors.toList());
		List<ExibicaoProdutoDTO> produtos = evento.getProdutos().stream().map(ExibicaoProdutoDTO::new).collect(Collectors.toList());
		
		DetalheEventoDTO detalheEventoDTO = new DetalheEventoDTO(evento.getId(),evento.getNome(), evento.getData(), evento.getLocal(), 
				participantes, produtos, valor.getValorTotal(), valor.getValorPorParticipante());
		
		return detalheEventoDTO;
	}
	
	public ValorAgredadoEvento calcularValorAgredado(Evento evento) {
		
		double valortotal = calcularValorTotal(evento);
		double valorPorParticipante = cacularValorPorParticipante(evento.getParticipantes(), valortotal);
		
		return new ValorAgredadoEvento(valortotal, valorPorParticipante);		
	}

	private double cacularValorPorParticipante(List<Participante> participantes, double valortotal) {
		
		return (double) valortotal/participantes.size();
	}

	private Double calcularValorTotal(Evento evento) {
		return evento.getProdutos().stream().mapToDouble(Produto::getPreco).sum();
	}
	
//	public DetalheEventoDTO adicionarProduto(AdicaoProdutosForm form, Long id){
//		
//
//	}
}
