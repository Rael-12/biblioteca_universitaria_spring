package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions;

public class BookCategoryNotFoundException extends RuntimeException {
    public BookCategoryNotFoundException(String message) {
        super(message);
    }
}
