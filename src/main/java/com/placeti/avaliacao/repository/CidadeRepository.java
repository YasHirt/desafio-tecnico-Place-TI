package com.placeti.avaliacao.repository;


//----------------------------------------------

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Repositório para entidade Cidade */
//----------------------------------------------
public interface CidadeRepository extends JpaRepository<Cidade,Long>  {
}
