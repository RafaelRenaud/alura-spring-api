package com.br.med.voll.api.model.dto.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ErrorHandlerDTO {

    private String statusCode;
    private String timestamp;
    private String traceId;
    private String message;
    private String details;
    private List<FieldError> errors;

    public ErrorHandlerDTO(HttpStatus httpStatus, Exception e){
        this.statusCode = String.valueOf(httpStatus.value());
        this.message = e.getMessage();
        this.details = e.getLocalizedMessage();
        this.timestamp = OffsetDateTime.now().toString();
        this.traceId = UUID.randomUUID().toString();
        this.errors = new ArrayList<>();
    }
}
