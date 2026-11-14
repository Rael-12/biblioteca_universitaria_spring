package com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.exceptions.BookNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.enums.LoanStates;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
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
    @JoinColumn(name = "id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "date_loan", nullable = false)
    private LocalDate dateLoan;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStates status;

    @Column(name = "renewed_count", nullable = false)
    private Long renewedCount;

    @PrePersist
    protected void onCreate() {
        this.dateLoan = LocalDate.now();
    }

    public static LoanEntity createLoan(UserEntity user, BookEntity book) {
        if(user == null)
            throw new UserNotFoundException("User must not be null");
        if(book == null)
            throw new BookNotFoundException("Book must not be null");

        return LoanEntity.builder()
                .user(user)
                .book(book)
                .renewedCount(0L)
                .status(LoanStates.ACTIVE)
                .dueDate(LocalDate.now().plusDays(user.getLoanDuration()))
                .build();
    }

    public void renewLoan(){
        if(this.status == LoanStates.RETURNED || this.status == LoanStates.RETURNED_LATE)
            throw new IllegalArgumentException("You cant renew this loan");

        if(this.renewedCount > 3)
            throw new IllegalArgumentException("Ypu cant renew this loan more times");

        this.renewedCount++;
        this.dueDate = this.dueDate.plusDays(user.getLoanDuration());
    }

    public void returnBook(){
        if(this.status == LoanStates.RETURNED || this.status == LoanStates.RETURNED_LATE)
            throw new IllegalArgumentException("You cant return this loan");

        if(LocalDate.now().isAfter(this.dueDate))
            this.status = LoanStates.RETURNED_LATE;
        else
            this.status = LoanStates.RETURNED;
        this.returnDate = LocalDate.now();
    }
}