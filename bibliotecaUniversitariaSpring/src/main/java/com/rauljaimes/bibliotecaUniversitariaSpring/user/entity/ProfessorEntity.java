package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity extends UserEntity {

    @Column(nullable = false, length = 100)
    private String department;
}
