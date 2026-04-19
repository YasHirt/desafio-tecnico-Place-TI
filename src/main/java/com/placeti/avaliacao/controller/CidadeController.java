package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.service.CidadeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------------------------------------
/** Endpoint para consultar e manter cidades */
//--------------------------------------------------
@RestController
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;
    public CidadeController(CidadeService ps)
    {
        this.cidadeService = ps;
    }
	//----------------------------------------------------------
	/** Endpoint que retorna uma cidade conforme seu ID */
	//----------------------------------------------------------
    @GetMapping("/{id}")
	public ResponseEntity<CidadeDTO> buscarPeloId(@PathVariable("id") @Min(1) Long id) {
		// TODO: Responde GET em http://localhost:8080/placeti/cidades/1
        // TODO: gerenciar status diferentes
        CidadeDTO cidadeDTO = cidadeService.pesquisarCidade(id);
        return ResponseEntity.ok(cidadeDTO);
	}
	//----------------------------------------------------------
	/** Endpoint que retorna todas as cidades cadastradas */
	//----------------------------------------------------------
    @GetMapping
	public List<CidadeDTO> pesquisarCidades() {
		// TODO: Responde GET em http://localhost:8080/placeti/cidades
        return cidadeService.pesquisarCidades();
	}
	//----------------------------------------------------------
	/** Endpoint para incluir nova cidade */
	//----------------------------------------------------------
    @PostMapping
	public void incluirCidade(@Valid @RequestBody CidadeDTO cidadeDto) {
		//	TODO: Responde POST em http://localhost:8080/placeti/cidades
        cidadeService.incluirCidade(cidadeDto);
	}
	
	//----------------------------------------------------------
	/** Endpoint para alterar cidade */
	//----------------------------------------------------------
    @PutMapping
	public void alterarCidade(@RequestBody CidadeDTO cidadeDto) {
		// TODO: Responde PUT em http://localhost:8080/placeti/cidades
        cidadeService.alterarCidade(cidadeDto);
	}
	//----------------------------------------------------------
	/** Endpoint para excluir uma cidade */
	//----------------------------------------------------------
    @DeleteMapping("/{idCidade}")
	public void excluirCidade(@PathVariable Long idCidade) {
		//  TODO Responde DELETE em http://localhost:8080/placeti/cidades/{idCidade}
        cidadeService.excluirCidade(idCidade);
	}
}
