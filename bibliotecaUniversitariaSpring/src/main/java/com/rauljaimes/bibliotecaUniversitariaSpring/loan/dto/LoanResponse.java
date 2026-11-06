package com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;

import java.time.LocalDate;

public record LoanResponse(
        Long id,
        UserResponse user,
        BookResponse book,
        Boolean active,
        LocalDate dateLoan,
        LocalDate dueDate,
        LocalDate returnDate,
        String status,
        Long renewedCount
) {
}
