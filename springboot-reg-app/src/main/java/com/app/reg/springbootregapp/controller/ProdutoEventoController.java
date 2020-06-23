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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.reg.springbootregapp.dominio.Evento;
import com.app.reg.springbootregapp.dominio.Produto;
import com.app.reg.springbootregapp.dto.DetalheEventoDTO;
import com.app.reg.springbootregapp.form.AdicaoProdutosForm;
import com.app.reg.springbootregapp.form.EdicaoProdutoForm;
import com.app.reg.springbootregapp.repository.EventoRepository;

@RestController
@RequestMapping("/eventos/{id}/produtos")
public class ProdutoEventoController {

	@Autowired
	private EventoRepository eventoRepository;
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeEventos", allEntries = true)
	public ResponseEntity<DetalheEventoDTO> adicionar(UriComponentsBuilder uriBuilder, @RequestBody @Valid AdicaoProdutosForm formProdutos, @PathVariable("id") String id){
		Optional<Evento> optionalEvento = eventoRepository.findById(new ObjectId(id));

		if(optionalEvento.isPresent()){
			Evento evento = optionalEvento.get();
			
			evento.getProdutos().addAll(formProdutos.converter());
			eventoRepository.save(evento);
			
			URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new DetalheEventoDTO(evento));
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable("id") String id, @RequestParam("nome") String nome){
		ObjectId objectId = new ObjectId(id);
		Optional<Evento> optional = eventoRepository.findById(objectId);
		
		if(optional.isPresent() ){
			Evento evento = optional.get();
			List<Produto> listaProdutos = evento.getProdutos(); 
			Optional<Produto> produtoOptional = listaProdutos.stream().filter(p-> p.getNome().equals(nome)).findAny();
			
			if(produtoOptional.isPresent()) {
				listaProdutos.remove(produtoOptional.get());
				
				eventoRepository.save(evento);
				
				return ResponseEntity.ok(new DetalheEventoDTO(evento));
			}
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> editar(@PathVariable("id") String id, @RequestParam("nome") String nome, @RequestBody @Valid EdicaoProdutoForm formProduto){
		ObjectId objectId = new ObjectId(id);
		Optional<Evento> optional = eventoRepository.findById(objectId);
		
		if(optional.isPresent() ){
			Evento evento = optional.get();
			List<Produto> listaProdutos = evento.getProdutos(); 
			Optional<Produto> produtoOptional = listaProdutos.stream().filter(p-> p.getNome().equals(nome)).findAny();
			
			if(produtoOptional.isPresent()) {
				formProduto.editar(produtoOptional.get());
				
				eventoRepository.save(evento);
				
				return ResponseEntity.ok(new DetalheEventoDTO(evento));
			}
		}
		return ResponseEntity.notFound().build(); 
	}
}
