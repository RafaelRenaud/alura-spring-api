package com.br.med.voll.api.exception;

public class InexistentEntityException extends RuntimeException {
    public InexistentEntityException(String message) {
        super(message);
    }
}
