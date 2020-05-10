package com.app.reg.springbootregapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.reg.springbootregapp.dominio.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	Page<Evento> findByNome(String nome, Pageable paginacao);


}
