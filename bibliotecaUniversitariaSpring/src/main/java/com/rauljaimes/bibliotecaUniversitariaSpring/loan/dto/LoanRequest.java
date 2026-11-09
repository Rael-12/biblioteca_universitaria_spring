package com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoanRequest(

        @NotNull(message = "The userId must not be null")
        @Positive(message = "The userId must be positive")
        Long userId,

        @NotNull(message = "The bookId must not be null")
        @Positive(message = "The bookId must be positive")
        Long bookId
) { }
