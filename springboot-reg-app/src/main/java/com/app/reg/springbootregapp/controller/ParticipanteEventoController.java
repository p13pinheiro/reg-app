package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dto.DetalheEventoDTO;
import com.app.reg.springbootregapp.form.AdicaoParticipanteForm;
import com.app.reg.springbootregapp.ifacade.IParticipanteFacade;
import com.app.reg.springbootregapp.repository.EventoRepository;

@RestController
@RequestMapping("/eventos/{id}/participantes")
public class ParticipanteEventoController {

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private IParticipanteFacade participanteFacade;
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<DetalheEventoDTO> adicionar(UriComponentsBuilder uriBuilder, @RequestBody @Valid AdicaoParticipanteForm adicaoParticipanteForm, @PathVariable String id) throws Exception{
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		if(optional.isPresent()){
			Evento eventoSalvo = participanteFacade.adicionar(adicaoParticipanteForm.getParticipantesForm(), optional.get());
			
			URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(eventoSalvo.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new DetalheEventoDTO(eventoSalvo)); 
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable String id, @RequestParam("nome") String nomeParticipante){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		
		if(optional.isPresent()) {
			Evento evento = optional.get();
			List<Participante> participantes = evento.getParticipantes();
			Optional<Participante> participanteOptional = participantes.stream().filter(p -> p.getNome().equals(nomeParticipante)).findAny();
			
			if(participanteOptional.isPresent()) {
				participantes.remove(participanteOptional.get());
				
				eventoRepository.save(evento);
				
				return ResponseEntity.ok(new DetalheEventoDTO(evento));
			}
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
}
