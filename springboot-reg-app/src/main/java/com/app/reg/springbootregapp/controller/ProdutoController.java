package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.reg.springbootregapp.dominio.Produto;
import com.app.reg.springbootregapp.dto.DetalheProdutoDTO;
import com.app.reg.springbootregapp.dto.ProdutoDTO;
import com.app.reg.springbootregapp.form.EdicaoProdutoForm;
import com.app.reg.springbootregapp.form.ProdutoForm;
import com.app.reg.springbootregapp.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<ProdutoDTO> listar(){
		 return ProdutoDTO.converter(produtoRepository.findAll());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDTO> criar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder builder){
		Produto produto= form.converter();
		produtoRepository.save(produto);
		
		URI uri = builder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
				
		return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheProdutoDTO> detalhar(@PathVariable("id") Long id){
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok().body(new DetalheProdutoDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> editar(@RequestBody @Valid EdicaoProdutoForm form, @PathVariable("id") Long id){
		Optional<Produto> optional = produtoRepository.findById(id);
	
		if(optional.isPresent()) {
			Produto produto = optional.get();
			form.editar(produto);
			
			return ResponseEntity.ok().body(new ProdutoDTO(produto));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if(optional.isPresent()) {
			produtoRepository.delete(optional.get());
			
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}
}
