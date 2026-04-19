package com.placeti.avaliacao.exceptions;

import com.placeti.avaliacao.dto.ApiErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        ApiErrorDTO error = new ApiErrorDTO(400, "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(ComercioNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> manipuladorComercioNotFoundExcetion(ComercioNotFoundException ex)
    {
        ApiErrorDTO error = new ApiErrorDTO(404, "NOT FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> manipuladorErroViolocaoDeConstraint(ConstraintViolationException ex)
    {
        ApiErrorDTO error = new ApiErrorDTO(400, "BAD REQUEST", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDTO> manipulaTipoIncorreto(MethodArgumentTypeMismatchException ex)
    {
        String message = "O parâmetro: " + ex.getName() + " deve ser um número válido";
        ApiErrorDTO error = new ApiErrorDTO(400, "Bad Request", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
