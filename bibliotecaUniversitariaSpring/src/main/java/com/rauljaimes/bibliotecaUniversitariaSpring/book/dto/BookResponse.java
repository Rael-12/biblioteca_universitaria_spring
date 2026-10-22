package com.rauljaimes.bibliotecaUniversitariaSpring.book.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer yearPublication,
        Boolean available,
        Integer amount,
        Integer amountAvailable,
        String isbn,
        String category,
        Boolean active
) {
}
