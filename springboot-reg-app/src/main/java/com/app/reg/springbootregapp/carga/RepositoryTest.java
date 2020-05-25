package com.app.reg.springbootregapp.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.reg.springbootregapp.repository.EventoRepository;

@Component
public class RepositoryTest implements ApplicationRunner {

	private static final long ID_EVENTO = 100L;
	private static final long ID_PARTICIPANTE_1 = 100L;
	private static final long ID_PARTICIPANTE_2 = 101L;
	private static final long ID_PARTICIPANTE_3 = 102L;
	private static final long ID_PRODUTO_1 = 100L;
	private static final long ID_PRODUTO_2 = 101L;
	private static final long ID_PRODUTO_3 = 102L;
	
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception{
		
//		System.out.println(" Iniciando carga de dados do evento ... ");
//		Participante participante1 = new Participante(ID_PARTICIPANTE_1, "Danielle Pinheiro", 29, "Vilas do Atlântico");
//		Participante participante2 = new Participante(ID_PARTICIPANTE_2, "Pêka Magalhães", 27, "Portugal");
//		Participante participante3 = new Participante(ID_PARTICIPANTE_3, "Karina Souza", 27, "Sociedade baixa");
//		List<Participante> participantes = new ArrayList<>();
//		participantes.add(participante1);
//		participantes.add(participante2);
//		participantes.add(participante3);
//		
//		Produto produto1 = new Produto(ID_PRODUTO_1, "CERVEJA", 5.50,20);
//		Produto produto2 = new Produto(ID_PRODUTO_2, "GELO", 15.50,1);
//		Produto produto3 = new Produto(ID_PRODUTO_3, "CHURRASCO", 100.00,1);
//		List<Produto> produtos = new ArrayList<>();
//		produtos.add(produto1);
//		produtos.add(produto2);
//		produtos.add(produto3);
//		
//		Evento evento = new Evento("HOME-CALL", new Date(), "Suas casinhas", participantes, produtos);
//		
//		System.out.println(" >>>> Primeiro Evento :  "+ evento);
//		
//		eventoRepository.save(evento);
		
//		System.out.println(" >>>> Primeiro Evento :  "+ evento);
	}
	
	
}
