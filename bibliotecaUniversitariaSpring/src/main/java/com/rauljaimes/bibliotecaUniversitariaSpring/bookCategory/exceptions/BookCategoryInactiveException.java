package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions;

public class BookCategoryInactiveException extends RuntimeException {
    public BookCategoryInactiveException(String message) {
        super(message);
    }
}
