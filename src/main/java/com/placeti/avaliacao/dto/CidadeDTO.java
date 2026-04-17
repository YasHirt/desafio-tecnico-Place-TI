package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Cidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO que guarda os dados de uma cidade
 */
public record CidadeDTO(
                        //validada no services para não gerar conflito entre post e put
                        Long id,
                        @NotBlank(message="Nome é obrigatório")
                        String nome,

                        @NotBlank(message = "Uf é obrigatória")
                        String uf,
                        @NotNull(message = "Campo capital é obrigatório")
                        boolean capital) {

}
