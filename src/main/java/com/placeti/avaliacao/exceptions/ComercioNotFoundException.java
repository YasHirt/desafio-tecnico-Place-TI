package com.placeti.avaliacao.exceptions;

public class ComercioNotFoundException extends IllegalArgumentException{
    public ComercioNotFoundException(String message)
    {
        super(message);
    }
}
