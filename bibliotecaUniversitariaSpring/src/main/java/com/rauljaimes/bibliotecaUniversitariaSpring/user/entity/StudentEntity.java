package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
