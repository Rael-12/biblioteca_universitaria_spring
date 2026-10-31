package com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IDataUser {
    Long id();

    String firstName();

    String lastName();

    String email();

    String phoneNumber();

    LocalDate birthdate();

    boolean active();

    LocalDateTime creationDate();

    LocalDateTime updated_at();
}
