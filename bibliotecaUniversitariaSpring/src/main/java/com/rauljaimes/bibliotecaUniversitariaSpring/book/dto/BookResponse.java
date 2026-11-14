package com.rauljaimes.bibliotecaUniversitariaSpring.book.dto;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer yearPublication,
        Boolean available,
        Integer amount,
        Integer amountAvailable,
        String isbn,
        BookCategoryResponse category,
        Boolean active
) {
}
