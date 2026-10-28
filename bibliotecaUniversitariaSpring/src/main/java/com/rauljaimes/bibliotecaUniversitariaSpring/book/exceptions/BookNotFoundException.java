package com.rauljaimes.bibliotecaUniversitariaSpring.book.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
