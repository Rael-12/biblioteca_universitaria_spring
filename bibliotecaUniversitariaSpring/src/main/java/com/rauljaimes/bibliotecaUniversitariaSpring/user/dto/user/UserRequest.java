package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user;

import java.time.LocalDate;
public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthdate,
        boolean active
) {
}
