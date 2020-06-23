package com.app.reg.springbootregapp.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.reg.springbootregapp.dominio.Evento;

public interface EventoRepository extends MongoRepository<Evento, ObjectId> {

	Page<Evento> findByNomeLike(String nome, Pageable paginacao);

	Optional<Evento> findByIdAndProdutosNome(ObjectId id,String nome);


}
