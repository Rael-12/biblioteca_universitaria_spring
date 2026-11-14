package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookCategoryRequest(

        @NotNull
        @NotBlank
        String categoryName
) {
}
