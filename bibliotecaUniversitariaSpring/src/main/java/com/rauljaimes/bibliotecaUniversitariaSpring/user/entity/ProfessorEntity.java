package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "professors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProfessorEntity extends UserEntity {

    @Column(nullable = false, length = 100)
    private String department;

    public static ProfessorEntity createProfessor(String firstName, String lastName, String email,
                                           String phoneNumber, LocalDate birthDate, String department) {
        return ProfessorEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .birthdate(birthDate)
                .loanBooks(0)
                .department(department)
                .build();
    }

    @Override
    public void loanBook(){
        if(this.getLoanBooks() > 10)
            throw new IllegalArgumentException("You can't loan less than 10 loan books");
        this.setLoanBooks(this.getLoanBooks() + 1);
    }

    @Override
    public Long getLoanDuration() {
        return 30L;
    }
}
