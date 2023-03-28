package com.br.med.voll.api.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
