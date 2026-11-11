package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentEntity extends UserEntity {

    @Column(nullable = false, length = 45)
    private String semester;

    @Column(nullable = false, length = 100)
    private String degree;

    public StudentEntity createStudent(
            String firstName, String lastName, String email,
            String phoneNumber, LocalDate birthDate, String semester,
            String degree
    ){
        return StudentEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .birthdate(birthDate)
                .active(true)
                .loanBooks(0)
                .semester(semester)
                .degree(degree)
                .build();
    }

    public void loanBook(){
        if(this.getLoanBooks() > 5)
            throw new IllegalArgumentException("You can't loan more books");
        this.setLoanBooks(this.getLoanBooks() + 1);
    }
}
