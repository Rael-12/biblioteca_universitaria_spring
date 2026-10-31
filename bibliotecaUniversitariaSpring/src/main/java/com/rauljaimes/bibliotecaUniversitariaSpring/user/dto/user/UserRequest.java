package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthdate,
        boolean active
) {
}
