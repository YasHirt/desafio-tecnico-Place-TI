package com.placeti.avaliacao.dto;

public record ApiErrorDTO(
                       int status,
                       String error,
                       String message
)  {}
