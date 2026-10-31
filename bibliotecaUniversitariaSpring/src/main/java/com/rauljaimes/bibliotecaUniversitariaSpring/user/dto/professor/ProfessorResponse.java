package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.IDataUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfessorResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthdate,
        boolean active,
        LocalDateTime creationDate,
        LocalDateTime updated_at
) implements IDataUser {
}
