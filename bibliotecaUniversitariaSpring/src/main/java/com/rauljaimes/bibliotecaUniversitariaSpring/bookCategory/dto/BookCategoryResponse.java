package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto;

public record BookCategoryResponse(
        Long categoryId,
        String categoryName,
        boolean active
) {
}
