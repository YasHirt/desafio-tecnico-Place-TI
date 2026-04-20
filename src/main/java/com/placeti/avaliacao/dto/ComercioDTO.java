package com.placeti.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//valida a entrada da API o mais cedo possível
public record ComercioDTO(
        Long id,
        @NotBlank(message="Nome é obrigatório")
        String nomeComercio,
        @NotBlank(message = "Responsável pelo comércio é obrigatório")
        String responsavelComercio,
        @NotBlank(message = "Tipo de Comércio é obrigatório")
        String tipoComercio,
        @NotNull(message = "Id cidade não pode ser nulo")
        Long idCidade){

        }
