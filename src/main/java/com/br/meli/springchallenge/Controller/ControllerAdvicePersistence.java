package com.br.meli.springchallenge.Controller;

import com.br.meli.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvicePersistence {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Object> hadlePersistencia(CustomException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }
}
