package com.app.reg.springbootregapp.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.reg.springbootregapp.dominio.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId>{
	
	public Optional<Usuario> findByEmail(String email);

}
