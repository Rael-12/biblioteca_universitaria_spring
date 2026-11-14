package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto;

public record BookCategoryResponse(
        Long id,
        String categoryName,
        boolean active
) {
}
