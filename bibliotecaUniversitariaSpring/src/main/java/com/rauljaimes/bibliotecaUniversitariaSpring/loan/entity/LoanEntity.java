package com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false )
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false )
    private BookEntity book;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "date_loan", nullable = false)
    private LocalDate dateLoan;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "renewed_count", nullable = false)
    private Long renewedCount;
}
