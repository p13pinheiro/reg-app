package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dominio.Produto;
import com.app.reg.springbootregapp.dto.DetalheEventoDTO;
import com.app.reg.springbootregapp.dto.EventoDTO;
import com.app.reg.springbootregapp.form.AdicaoParticipanteForm;
import com.app.reg.springbootregapp.form.AdicaoProdutosForm;
import com.app.reg.springbootregapp.form.EdicaoEventoForm;
import com.app.reg.springbootregapp.form.EdicaoProdutoForm;
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
	public Page<EventoDTO> listar(@RequestParam(required = false) String nome , @PageableDefault(page = 0, sort = "data", size= 10, direction = Direction.ASC) Pageable paginacao){
		if(nome == null) {
			return EventoDTO.converter(eventoRepository.findAll(paginacao));
		}
		
		return EventoDTO.converter(eventoRepository.findByNome(nome, paginacao));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<EventoDTO> criar(@RequestBody @Valid EventoForm form, UriComponentsBuilder uriBuilder) {
		Evento evento = form.converter();
		eventoRepository.save(evento);
		
		URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
		return ResponseEntity.created(uri).body(new EventoDTO(evento));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheEventoDTO> detalhar(@PathVariable String id){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		
		if(optional.isPresent()) {
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(optional.get());
			return ResponseEntity.ok(detalheEventoDTO);
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
	
	@PutMapping("/{id}/participantes")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<DetalheEventoDTO> adicionarParticipante(@RequestBody @Valid AdicaoParticipanteForm form, @PathVariable String id){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		if(optional.isPresent()){
			Evento evento = optional.get();
			
			List<Participante> participantes = form.converter();
	
			evento.getParticipantes().addAll(participantes);
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
			eventoRepository.save(evento);
			return ResponseEntity.ok(detalheEventoDTO); 
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@PutMapping("/{id}/produtos")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<DetalheEventoDTO> adicionarProduto(@RequestBody @Valid AdicaoProdutosForm form, @PathVariable("id") String id){
		Optional<Evento> optionalEvento = eventoRepository.findById(new ObjectId(id));

		if(optionalEvento.isPresent()){
			Evento evento = optionalEvento.get();
			List<Produto> produtos = form.converter();
			evento.getProdutos().addAll(produtos);
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
			eventoRepository.save(evento);
			return ResponseEntity.ok(detalheEventoDTO);
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("{id}/produtos")
	@Transactional
	public ResponseEntity<?> excluirProduto(@PathVariable String id, @PathParam("nome") String nome){
		ObjectId objectId = new ObjectId(id);
		Optional<Evento> optional = eventoRepository.findById(objectId);
		
		if(optional.isPresent() ){
			Evento evento = optional.get();
			List<Produto> listaProdutos = evento.getProdutos(); 
			Optional<Produto> produtoOptional = listaProdutos.stream().filter(p-> p.getNome().equals(nome)).findAny();
			
			if(produtoOptional.isPresent()) {
				listaProdutos.remove(produtoOptional.get());
				
				DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
				eventoRepository.save(evento);
				return ResponseEntity.ok(detalheEventoDTO);
			}
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("{id}/participantes")
	@Transactional
	public ResponseEntity<?> excluirParticipantes(@PathVariable String id, @PathParam("nome") String nome){
		Optional<Evento> optional = eventoRepository.findById(new ObjectId(id));
		
		if(optional.isPresent()) {
			Evento evento = optional.get();
			List<Participante> participantes = evento.getParticipantes();
			Optional<Participante> participanteOptional = participantes.stream().filter(p -> p.getNome().equals(nome)).findAny();
			
			if(participanteOptional.isPresent()) {
				participantes.remove(participanteOptional.get());
				
				DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
				eventoRepository.save(evento);
				return ResponseEntity.ok(detalheEventoDTO);
			}
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@PutMapping("{id}/produto")
	@Transactional
	public ResponseEntity<?> editarProduto(@PathVariable String id, @PathParam("nome") String nome, @RequestBody @Valid EdicaoProdutoForm form){
		ObjectId objectId = new ObjectId(id);
		Optional<Evento> optional = eventoRepository.findById(objectId);
		
		if(optional.isPresent() ){
			Evento evento = optional.get();
			List<Produto> listaProdutos = evento.getProdutos(); 
			Optional<Produto> produtoOptional = listaProdutos.stream().filter(p-> p.getNome().equals(nome)).findAny();
			
			if(produtoOptional.isPresent()) {
				form.editar(produtoOptional.get());
				
				DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
				eventoRepository.save(evento);
				return ResponseEntity.ok(detalheEventoDTO);
			}
		}
		return ResponseEntity.notFound().build(); 
	}
}
