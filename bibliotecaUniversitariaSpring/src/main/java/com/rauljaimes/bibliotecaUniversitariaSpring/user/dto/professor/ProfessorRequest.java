package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProfessorRequest(

        @NotNull(message = "The firstName must not be null")
        @NotBlank(message = "The firstName must not be blank")
        @Size(max = 50, message = "The firstName maximum size is 50")
        String firstName,

        @NotNull(message = "The lastName must not be null")
        @NotBlank(message = "The lastName must not be blank")
        @Size(max = 50, message = "The lastName maximum size is 50")
        String lastName,

        @NotNull(message = "The email must not be null")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "The phoneNumber must not be null")
        @Digits(integer = 10, fraction = 0, message = "The maximum numbers of digits is 10")
        int phoneNumber,

        @NotNull(message = "The department must not be null")
        @NotBlank(message = "The department must not be blank")
        @Size(max = 100, message = "The department maximum size is 100")
        String department,

        @NotNull(message = "The birthdate must not be null")
        @Past(message = "The date of birth must be earlier than the current date")
        LocalDate birthdate
) {
}
