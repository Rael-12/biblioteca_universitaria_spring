package com.rauljaimes.bibliotecaUniversitariaSpring.user.entity;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name", nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 10, unique =  true)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @Column(nullable = false)
    private int loanBooks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LoanEntity> loans = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}