package com.joel.br.CatalogExpert.exceptions;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
