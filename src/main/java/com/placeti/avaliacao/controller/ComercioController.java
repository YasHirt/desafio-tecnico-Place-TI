package com.placeti.avaliacao.controller;
import com.placeti.avaliacao.dto.ComercioDTO;

import com.placeti.avaliacao.service.ComercioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercios")
@Validated

public class ComercioController {
    private final ComercioService comercioService;
    public ComercioController(ComercioService cs)
    {
        this.comercioService = cs;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ComercioDTO> buscarComercio(@PathVariable @Min(1) Long id)
    {
        return ResponseEntity.ok(comercioService.buscarComercio(id));
    }
    @GetMapping
    public ResponseEntity<List<ComercioDTO>> buscarComercios()
    {
        return ResponseEntity.ok(comercioService.buscarComercios());
    }
    @PostMapping
    public ResponseEntity<ComercioDTO> incluirComercio(@RequestBody @Valid ComercioDTO comercioDTO)
    {
        //Exemplo de requisição
//        {
//                "nomeComercio": "Comércio dos Momolangos",
//                "responsavelComercio": "Yasmin e Maximus",
//                "tipoComercio": "Cafeteria"
//        }
        ComercioDTO comercioResponse = comercioService.incluirComercio(comercioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comercioResponse);
    }
    @PutMapping
    public ResponseEntity<ComercioDTO> alterarComercio(@RequestBody @Valid ComercioDTO comercioDTO)
    {
        return ResponseEntity.ok(comercioService.alterarComercio(comercioDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComercio(@PathVariable @Min(1) Long id)
    {
        comercioService.excluirComercio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
