package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------------------------------------
/** Endpoint para consultar e manter cidades */
//--------------------------------------------------
@RestController //retorno dos métodos é resposta HTTP, spring converte para Json
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
	public ResponseEntity<CidadeDTO> buscarPeloId(@PathVariable("id") Long id) {
		// TODO: Responde GET em http://localhost:8080/placeti/cidades/1
        CidadeDTO cidadeDTO = cidadeService.pesquisarCidade(id);
        return ResponseEntity.ok(cidadeDTO);
	}
	
	//----------------------------------------------------------
	/** Endpoint que retorna todas as cidades cadastradas */
	//----------------------------------------------------------
//	public List<CidadeDTO> pesquisarCidades() {
//		// TODO: Responde GET em http://localhost:8080/placeti/cidades
//	}
//
	//----------------------------------------------------------
	/** Endpoint para incluir nova cidade */
	//----------------------------------------------------------
//	public void incluirCidade(CidadeDTO cidadeDto) {
//		//	TODO: Responde POST em http://localhost:8080/placeti/cidades
//		//	Envia JSON no body:
//		//	{
//		//	 	"nome": "Florianópolis",
//		//	  	"uf": "SC",
//		//	   	"capital": true
//		//	}
//	}
	
	//----------------------------------------------------------
	/** Endpoint para alterar cidade */
	//----------------------------------------------------------
//	public void alterarCidade(CidadeDTO cidadeDto) {
		// TODO: Responde PUT em http://localhost:8080/placeti/cidades
		//   Envia JSON no body:
		//   {
		//     "id": 11,
		//     "nome": "Blumenau",
		//     "uf": "SC",
		//     "capital": false
		//   }
	//}
	
	//----------------------------------------------------------
	/** Endpoint para excluir uma cidade */
	//----------------------------------------------------------
//	public void excluirCidade(Long idCidade) {
//		// Responde DELETE em http://localhost:8080/placeti/cidades/{idCidade}
//	}
}
