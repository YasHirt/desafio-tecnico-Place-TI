package com.placeti.avaliacao.exceptions;

public class CityNotFoundException extends IllegalArgumentException{
    public CityNotFoundException(String message)
    {
        super(message);
    }
}
