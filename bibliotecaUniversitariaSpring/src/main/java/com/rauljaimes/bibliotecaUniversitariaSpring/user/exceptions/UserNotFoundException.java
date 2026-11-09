package com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
