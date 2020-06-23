package com.app.reg.springbootregapp.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.reg.springbootregapp.dominio.Usuario;
import com.app.reg.springbootregapp.dto.CadastroUsuarioDTO;
import com.app.reg.springbootregapp.form.CadastroUsuarioForm;
import com.app.reg.springbootregapp.form.EdicaoUsuarioForm;
import com.app.reg.springbootregapp.repository.UsuarioRepository;

@RestController
@RequestMapping("/cadastro/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EventoController eventoController;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroUsuarioForm form, UriComponentsBuilder builder){
		Usuario usuario = form.converter(passwordEncoder);
		usuarioRepository.save(usuario);

		//por enquanto irá retornar apenas para a listagem dos eventos que ja existem 
		//(eventos publicos - ainda nao implementado como isso será validado)
		Pageable pag = Pageable.unpaged();
		URI uri = builder.path("/cadastro/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(eventoController.listar(null, pag));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable String id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(new ObjectId(id));
		if(usuarioOptional.isPresent()) {
			return ResponseEntity.ok(new CadastroUsuarioDTO(usuarioOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody @Valid EdicaoUsuarioForm form, @PathVariable String id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(new ObjectId(id));
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			form.editar(usuario);
			usuarioRepository.save(usuario);
			
			return ResponseEntity.ok(new CadastroUsuarioDTO(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
}
