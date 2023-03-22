package com.br.med.voll.api.controller;

import com.br.med.voll.api.model.dto.error.ErrorFieldDTO;
import com.br.med.voll.api.model.dto.error.ErrorHandlerDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException e){

        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorFieldDTO> errors = new ArrayList<>();

        for(FieldError fieldError : fieldErrors){
            errors.add(new ErrorFieldDTO(fieldError));
        }

        ErrorHandlerDTO errorHandlerDTO = new ErrorHandlerDTO(HttpStatus.BAD_REQUEST,e);
        return ResponseEntity.badRequest().body(errorHandlerDTO);
    }
}
