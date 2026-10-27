package com.rauljaimes.bibliotecaUniversitariaSpring.book.dto;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.anotation.ValidPublicationYear;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.ISBN;

public record BookRequest(

        @NotNull(message = "The title must not be null")
        @NotBlank(message = "The title must not contain blank spaces")
        @Size(max = 100, message = "The maximum size must be 100 characters")
        String title,

        @NotNull(message = "The author must not be null")
        @NotBlank(message = "The author must no contain blank spaces")
        @Size(max = 100, message = "The maximum size must be 100 characters")
        String author,

        @NotNull(message = "The year must no be null")
        @Min(value = 1500, message = "The year minimum must be 1500")
        @ValidPublicationYear
        Integer yearPublication,

        @NotNull(message = "The amount must not be null")
        @PositiveOrZero(message = "The amount must be zero o greater than zero")
        Integer amount,

        @NotBlank(message = "The ISBN must no contain blank spaces")
        @ISBN(message = "The ISBN format is invalid")
        String isbn,

        @NotNull(message = "The category is required")
        String category
) {
}
