package com.Store.Store.backend.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerArgument(IllegalArgumentException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ClientExistsException.class)
    public ResponseEntity<String> handleClientExistsException(ClientExistsException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }



}
