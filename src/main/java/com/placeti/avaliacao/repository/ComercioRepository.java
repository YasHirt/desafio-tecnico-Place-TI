package com.placeti.avaliacao.repository;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
    List<Comercio> findByCidadeId(Long id_cidade);
    List<Comercio> findByTipoComercio(String tipoComercio);
}
