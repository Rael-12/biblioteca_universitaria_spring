package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends User{

    @Column(nullable = false, length = 45)
    private String semester;

    @Column(nullable = false, length = 100)
    private String degree;
}
