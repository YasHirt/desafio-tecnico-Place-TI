package com.placeti.avaliacao.Exceptions;

public class ComercioNotFoundException extends IllegalArgumentException{
    public ComercioNotFoundException(String message)
    {
        super(message);
    }
}
