package com.app.reg.springbootregapp.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.app.reg.springbootregapp.dominio.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${reg.jwt.expiration}")
	private String expiration;
	
	@Value("${reg.jwt.secret}")
	private String secret;
	
	
	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date dtAtual = new Date();
		Date dtExpiracao = new Date(dtAtual.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("API REG")
		.setSubject(usuario.getId().toString())
		.setIssuedAt(dtAtual)
		.setExpiration(dtExpiracao)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
	}


	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public Long obterIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
