package com.br.med.voll.api.model.dto.error;

import org.springframework.validation.FieldError;

public class ErrorFieldDTO {
    private String field;
    private String message;

    public ErrorFieldDTO(FieldError fieldError){
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
