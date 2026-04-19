package com.placeti.avaliacao;

import com.placeti.avaliacao.Exceptions.CityNotFoundException;
import com.placeti.avaliacao.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorGlobalDeExcecoes {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> manipulaCityNotFoundException(CityNotFoundException ex)
    {
        ApiErrorDTO error = new ApiErrorDTO(404, "Not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDTO> manipuladorIllegalArgumentException(IllegalArgumentException ex)
    {
        ApiErrorDTO error = new ApiErrorDTO(400, "Bad Resquest", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
