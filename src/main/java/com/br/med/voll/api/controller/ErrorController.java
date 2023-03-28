package com.br.med.voll.api.controller;

import com.br.med.voll.api.exception.InexistentEntityException;
import com.br.med.voll.api.exception.ValidationException;
import com.br.med.voll.api.model.dto.error.ErrorFieldDTO;
import com.br.med.voll.api.model.dto.error.ErrorHandlerDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity notFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity badRequest(MethodArgumentNotValidException e){

        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorFieldDTO> errors = new ArrayList<>();

        for(FieldError fieldError : fieldErrors){
            errors.add(new ErrorFieldDTO(fieldError));
        }

        ErrorHandlerDTO errorHandlerDTO = new ErrorHandlerDTO(HttpStatus.BAD_REQUEST,e);
        return ResponseEntity.badRequest().body(errorHandlerDTO);
    }

    @ExceptionHandler({InexistentEntityException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorHandlerDTO> badRequest(Exception e){
        ErrorHandlerDTO errorHandlerDTO = new ErrorHandlerDTO(HttpStatus.BAD_REQUEST, e);
        return ResponseEntity.badRequest().body(errorHandlerDTO);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<ErrorHandlerDTO> unprocessableEntity(Exception e){
        ErrorHandlerDTO errorHandlerDTO = new ErrorHandlerDTO(HttpStatus.UNPROCESSABLE_ENTITY, e);
        return ResponseEntity.unprocessableEntity().body(errorHandlerDTO);
    }
}
