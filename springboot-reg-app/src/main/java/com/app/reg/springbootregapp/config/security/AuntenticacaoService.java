package com.app.reg.springbootregapp.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Usuario;
import com.app.reg.springbootregapp.repository.UsuarioRepository;

@Service
public class AuntenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if(usuario != null) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Usuário Inválido!");
	}

}
