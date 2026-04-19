package com.placeti.avaliacao.service;

import com.placeti.avaliacao.Exceptions.ComercioNotFoundException;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComercioService {
    private final ComercioRepository comercioRepository;
    public ComercioService(ComercioRepository cr)
    {
        this.comercioRepository = cr;
    }
    private static Comercio toEntity(ComercioDTO c) {
        Comercio comercio = new Comercio();
        comercio.setNomeComercio(c.nomeComercio());
        comercio.setResponsavelComercio(c.responsavelComercio());
        comercio.setTipoComercio(c.tipoComercio());
        return comercio;
    }
    private static ComercioDTO toDTO(Comercio c)
    {
        return new ComercioDTO(c.getId(), c.getNomeComercio(), c.getResponsavelComercio(), c.getTipoComercio());
    }
    public List<ComercioDTO> buscarComercios()
    {
        return comercioRepository.findAll().stream()
                .map(ComercioService::toDTO)
                .toList();
    }
    public ComercioDTO buscarComercio(Long id)
    {
        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow(() -> new ComercioNotFoundException("Comercio não encontrado com esse ID"));
        return toDTO(comercio);

    }
    public ComercioDTO incluirComercio(ComercioDTO c)
    {
        if(c.id() != null)
        {
            throw new IllegalArgumentException("Para incluir comércio, ID deve ser nulo");
        }
        Comercio comercio = toEntity(c);
        Comercio comercioPersistido = comercioRepository.save(comercio);
        return toDTO(comercioPersistido);
    }



    public ComercioDTO alterarComercio(ComercioDTO c)
    {
        if (c.id() == null)
        {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        Comercio comercioExistente = comercioRepository.findById(c.id())
                .orElseThrow(() -> new ComercioNotFoundException("Comercio não encontrado com esse id"));
        comercioExistente.setNomeComercio(c.nomeComercio());
        comercioExistente.setResponsavelComercio(c.responsavelComercio());
        comercioExistente.setTipoComercio(c.tipoComercio());
        Comercio comercioPersistido = comercioRepository.save(comercioExistente);
        return toDTO(comercioPersistido);
    }
    public void excluirComercio(Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        comercioRepository.findById(id)
            .orElseThrow(() -> new ComercioNotFoundException("Comercio não encontrado com esse ID"));
        comercioRepository.deleteById(id);
    }
}
