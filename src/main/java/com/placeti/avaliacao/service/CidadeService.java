package com.placeti.avaliacao.service;

import com.placeti.avaliacao.exceptions.CityNotFoundException;
import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
//------------------------------------------------------------------
/** Service usado para acessar os repositórios da aplicação */
//------------------------------------------------------------------
@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());

    public CidadeService(CidadeRepository cp) {
        this.cidadeRepository = cp;
    }
	//---------------------------------------------------------
	/** Método que busca uma cidade pelo seu ID */
	//---------------------------------------------------------
	public CidadeDTO pesquisarCidade(Long id) {

        Cidade c = cidadeRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException("Cidade não encontrada com id: " + id)
        );
        return new CidadeDTO(c.getId(), c.getNome(), c.getUf(), c.getCapital());
    }
	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas */
	//---------------------------------------------------------
	public List<CidadeDTO> pesquisarCidades() {
           return  cidadeRepository.findAll()
                    .stream()
                    .map(c -> new CidadeDTO(c.getId(), c.getNome(), c.getUf(), c.getCapital()))
                    .toList();
	}
	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------	
	public void incluirCidade(CidadeDTO dto) {
        if (dto.id() != null)
        {
           throw new IllegalArgumentException("Id deve ser null ao criar uma cidade");
        }
        Cidade c = new Cidade();
        c.setNome(dto.nome());
        c.setUf(dto.uf());
        c.setCapital(dto.capital());
        cidadeRepository.save(c);
	}
	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------
	public void alterarCidade(CidadeDTO dto) {
            if (dto.id() == null)
            {
                throw new IllegalArgumentException("Id não pode ser nulo");
            }
            Cidade cidadeExistente = cidadeRepository.findById(dto.id())
                    .orElseThrow(() -> new CityNotFoundException("Cidade não encontrada"));
            cidadeExistente.setNome(dto.nome());
            cidadeExistente.setCapital(dto.capital());
            cidadeExistente.setUf(dto.uf());
            cidadeRepository.save(cidadeExistente); //segurança
	}
	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------	
	public void excluirCidade(Long idCidade) {
            if (idCidade == null)
            {
                throw new IllegalArgumentException("Id não pode ser nulo");
            }
           cidadeRepository.findById(idCidade)
                    .orElseThrow(() -> new CityNotFoundException("Cidade não encontrada com esse ID"));
            cidadeRepository.deleteById(idCidade);

	}
    //----------------------------------------------------------
    /** Método usado para transferir dados entre camadas internas */
    //----------------------------------------------------------

    public Cidade buscarEntidadePorId(@NotNull(message = "Id cidade não pode ser nulo") Long idCidade) {
        return cidadeRepository.findById(idCidade)
                .orElseThrow(() -> new CityNotFoundException("Cidade não encontrada com esse id"));
    }
}
