package com.app.reg.springbootregapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.reg.springbootregapp.dominio.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

	List<Participante> findByNome(String nome);

}
