package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dto.DetalheEventoDTO;
import com.app.reg.springbootregapp.dto.EventoDTO;
import com.app.reg.springbootregapp.form.EdicaoEventoForm;
import com.app.reg.springbootregapp.form.EventoForm;
import com.app.reg.springbootregapp.ifacade.IEventoFacade;
import com.app.reg.springbootregapp.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository; 
	@Autowired
	private IEventoFacade eventoFacade;
	
	@GetMapping
	@Cacheable(value = "listaDeEventos")
	public Page<EventoDTO> listar(@RequestParam(name="nome", required = false) String nome , @PageableDefault(page = 0, sort = "data", size= 10, direction = Direction.ASC) Pageable paginacao){
		if(nome == null) {
			return EventoDTO.converter(eventoRepository.findAll(paginacao));
		}
		
		return EventoDTO.converter(eventoRepository.findByNomeLike(nome, paginacao));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<EventoDTO> criar(@RequestBody @Valid EventoForm form, UriComponentsBuilder uriBuilder) throws Exception {
		Evento evento = eventoFacade.inserirDadosEvento(form);
		
		URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
		return ResponseEntity.created(uri).body(new EventoDTO(evento));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheEventoDTO> detalhar(@PathVariable String id){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(new DetalheEventoDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<EventoDTO> editar(@PathVariable String id, @RequestBody @Valid EdicaoEventoForm form){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		
		if(optional.isPresent()) {
			Evento evento = optional.get();
			form.editar(evento);
			eventoRepository.save(evento);
			return ResponseEntity.ok(new EventoDTO(evento));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<?> excluir(@PathVariable String id){
		ObjectId idObj = new ObjectId(id);
		Optional<Evento> optional = eventoRepository.findById(idObj);
		
		if(optional.isPresent()) {
			eventoRepository.deleteById(idObj);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
