package com.Store.Store.backend.exceptionHandler;

public class ClientExistsException extends RuntimeException {
    public ClientExistsException(String message) {
        super(message);
    }
}

