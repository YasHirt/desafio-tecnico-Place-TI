package com.placeti.avaliacao.service;

import com.placeti.avaliacao.exceptions.ComercioNotFoundException;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComercioService {

    private final ComercioRepository comercioRepository;
    private final CidadeService cidadeService;

    public ComercioService(ComercioRepository cr, CidadeService cs)
    {
        this.comercioRepository = cr;
        this.cidadeService = cs;
    }
    private Comercio toEntity(ComercioDTO c) {
        Cidade cidade = cidadeService.buscarEntidadePorId(c.idCidade());
        Comercio comercio = new Comercio();
        comercio.setNomeComercio(c.nomeComercio());
        comercio.setResponsavelComercio(c.responsavelComercio());
        comercio.setTipoComercio(c.tipoComercio());
        comercio.setCidade(cidade);
        return comercio;
    }
    private ComercioDTO toDTO(Comercio c)
    {
        return new ComercioDTO(c.getId(), c.getNomeComercio(), c.getResponsavelComercio(), c.getTipoComercio(), c.getCidade().getId());
    }
    public List<ComercioDTO> buscarComercios()
    {
        return comercioRepository.findAll().stream()
                .map(this::toDTO)
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
        Cidade cidade = cidadeService.buscarEntidadePorId(c.idCidade());
        Comercio comercioExistente = comercioRepository.findById(c.id())
                .orElseThrow(() -> new ComercioNotFoundException("Comercio não encontrado com esse id"));
        comercioExistente.setNomeComercio(c.nomeComercio());
        comercioExistente.setResponsavelComercio(c.responsavelComercio());
        comercioExistente.setTipoComercio(c.tipoComercio());
        comercioExistente.setCidade(cidade);
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
    public List<ComercioDTO> buscarComerciosPorIdCidadeETipo(Long idCidade, String tipo)
    {
        cidadeService.buscarEntidadePorId(idCidade); //valida existencia
        if(tipo == null || tipo.isBlank())
        {
            return comercioRepository.findByCidadeId(idCidade).stream()
                    .map(this::toDTO)
                    .toList();
        }
        return comercioRepository.findByTipoComercio(tipo)
                .stream()
                .map(this::toDTO)
                .toList();

    }
}
