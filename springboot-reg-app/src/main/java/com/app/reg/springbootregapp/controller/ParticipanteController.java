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

import com.app.reg.springbootregapp.dominio.Participante;
import com.app.reg.springbootregapp.dto.DetalheParticipanteDTO;
import com.app.reg.springbootregapp.dto.ParticipanteDTO;
import com.app.reg.springbootregapp.form.EdicaoParticipanteForm;
import com.app.reg.springbootregapp.form.ParticipanteForm;
import com.app.reg.springbootregapp.repository.ParticipanteRepository;

@RestController	
@RequestMapping("/participantes")
public class ParticipanteController {
	
	@Autowired
	private ParticipanteRepository participanteRepository;

	@GetMapping
	public List<ParticipanteDTO> listar(){
		 return ParticipanteDTO.converter(participanteRepository.findAll());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ParticipanteDTO> criar(@RequestBody @Valid ParticipanteForm form, UriComponentsBuilder builder){
		Participante participante = form.converter();
		participanteRepository.save(participante);
		
		URI uri = builder.path("/participantes/{id}").buildAndExpand(participante.getId()).toUri();
				
		return ResponseEntity.created(uri).body(new ParticipanteDTO(participante));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheParticipanteDTO> detalhar(@PathVariable("id") Long id){
		Optional<Participante> optional = participanteRepository.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok().body(new DetalheParticipanteDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ParticipanteDTO> editar(@RequestBody @Valid EdicaoParticipanteForm form, @PathVariable("id") Long id){
		Optional<Participante> optional = participanteRepository.findById(id);
	
		if(optional.isPresent()) {
			Participante participante = optional.get();
			form.editar(participante);
			
			return ResponseEntity.ok().body(new ParticipanteDTO(participante));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Participante> optional = participanteRepository.findById(id);
		
		if(optional.isPresent()) {
			participanteRepository.delete(optional.get());
			
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}
	
}
