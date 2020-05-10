package com.app.reg.springbootregapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.reg.springbootregapp.dominio.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
