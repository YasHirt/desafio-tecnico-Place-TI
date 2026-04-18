package com.placeti.avaliacao.Exceptions;

public class CityNotFoundException extends IllegalArgumentException{
    public CityNotFoundException(String message)
    {
        super(message);
    }
}
