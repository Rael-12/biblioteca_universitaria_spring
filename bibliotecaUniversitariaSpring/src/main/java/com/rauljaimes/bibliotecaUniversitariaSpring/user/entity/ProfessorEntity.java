package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
