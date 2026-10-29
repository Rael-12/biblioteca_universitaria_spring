package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.IDataUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthdate,
        String degree,
        String semester,
        boolean active,
        LocalDateTime creationDate,
        LocalDateTime updated_at

) implements IDataUser {
}
