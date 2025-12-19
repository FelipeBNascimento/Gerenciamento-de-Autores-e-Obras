package com.javanauta.desafio3.infrasctruture.exceptions;

public class EmailExistente extends RuntimeException {
    public EmailExistente(String message) {
        super(message);
    }
}
