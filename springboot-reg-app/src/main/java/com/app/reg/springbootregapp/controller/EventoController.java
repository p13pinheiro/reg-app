package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import com.app.reg.springbootregapp.form.EventoForm;
import com.app.reg.springbootregapp.ifacade.IEventoFacade;
import com.app.reg.springbootregapp.repository.EventoRepository;
import com.app.reg.springbootregapp.repository.ParticipanteRepository;
import com.app.reg.springbootregapp.repository.ProdutoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository; 
	@Autowired
	private ParticipanteRepository participanteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
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
		Evento evento = form.converter(participanteRepository,produtoRepository);
		eventoRepository.save(evento);
		
		URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
		return ResponseEntity.created(uri).body(new EventoDTO(evento));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheEventoDTO> detalhar(@PathVariable Long id){
		Optional<Evento> optional = eventoRepository.findById(id);
		
		if(optional.isPresent()) {
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(optional.get());
			return ResponseEntity.ok(detalheEventoDTO);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<EventoDTO> editar(@PathVariable Long id, @RequestBody @Valid EdicaoEventoForm form){
		Optional<Evento> optional = eventoRepository.findById(id);
		
		if(optional.isPresent()) {
			Evento evento = optional.get();
			form.editar(evento);
			
			return ResponseEntity.ok(new EventoDTO(evento));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Evento> optional = eventoRepository.findById(id);
		if(optional.isPresent()) {
			eventoRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}/participantes")
	@Transactional
	public ResponseEntity<DetalheEventoDTO> adicionarParticipante(@RequestBody @Valid 	AdicaoParticipanteForm form, @PathVariable("id") Long id){
		List<Participante> participantes = new ArrayList<Participante>();
		Optional<Evento> optionalEvento = eventoRepository.findById(id);
		if(optionalEvento.isPresent()){
			Evento evento = optionalEvento.get();
			
			form.getParticipanteIds().forEach(p -> {
				Optional<Participante> optional = participanteRepository.findById(p);
				if(optional.isPresent()) {
					participantes.add(optional.get());
				}
			});
	
			evento.getParticipantes().addAll(participantes);
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
			
			return ResponseEntity.ok(detalheEventoDTO); 
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@PostMapping("/{id}/produtos")
	@Transactional
	public ResponseEntity<DetalheEventoDTO> adicionarProduto(@RequestBody @Valid AdicaoProdutosForm form, @PathVariable("id") Long id){
		List<Produto> produtos = new ArrayList<Produto>();
		Optional<Evento> optionalEvento = eventoRepository.findById(id);

		if(optionalEvento.isPresent()){
			Evento evento = optionalEvento.get();
			form.getProdutoIds().forEach(p -> {
				Optional<Produto> optional = produtoRepository.findById(p);
				if(optional.isPresent()) {
					produtos.add(optional.get());
				}
			});
			
			evento.getProdutos().addAll(produtos);
			DetalheEventoDTO detalheEventoDTO = eventoFacade.preencherCamposEvento(evento);
			return ResponseEntity.ok(detalheEventoDTO);
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("{id}/produtos/{idProduto}")
	@Transactional
	public ResponseEntity<?> excluirProdutos(@PathVariable("id") Long id, @PathVariable("idProduto") Long idProduto){
		Optional<Evento> optional = eventoRepository.findById(id);
		Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
		
		if(optional.isPresent() && produtoOptional.isPresent()){
			optional.get().getProdutos().remove(produtoOptional.get());
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("{id}/participantes/{idParticipante}")
	@Transactional
	public ResponseEntity<?> excluirParticipantes(@PathVariable("id") Long id, @PathVariable("idParticipante") Long idParticipante){
		Optional<Evento> optional = eventoRepository.findById(id);
		Optional<Participante> participanteOptional = participanteRepository.findById(idParticipante);
		
		if(optional.isPresent() && participanteOptional.isPresent()){
			optional.get().getParticipantes().remove(participanteOptional.get());
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); 
	}
}
