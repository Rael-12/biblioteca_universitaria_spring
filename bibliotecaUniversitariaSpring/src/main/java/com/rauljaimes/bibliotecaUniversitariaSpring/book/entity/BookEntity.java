package com.rauljaimes.bibliotecaUniversitariaSpring.book.entity;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "year_publication", nullable = false)
    private Integer yearPublication;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "amount_available", nullable = false)
    private Integer amountAvailable;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "category", nullable = false)
    //@Enumerated
    private String category;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<LoanEntity> loans = new ArrayList<>();


    public BookEntity createBook(String title, String author,
                                 Integer yearPublication, Integer amount,
                                 String isbn, String category) {

        return BookEntity.builder()
                .title(title)
                .author(author)
                .yearPublication(yearPublication)
                .available(true)
                .amount(amount)
                .amountAvailable(amount)
                .isbn(isbn)
                .category(category)
                .active(true)
                .build();
    }

    public void loanBook() {
        if (this.amountAvailable <= 0)
            throw new IllegalStateException("amount is negative");

        this.amountAvailable--;
    }

    public void returnBook() {
        if (amountAvailable >= amount)
            throw new IllegalStateException("amount is greater than amount");

        this.amountAvailable++;

    }

    public void updateBook(String title, String author,
                           Integer yearPublication, Integer amount,
                           String isbn, String category) {
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
        this.amount = amount;
        this.isbn = isbn;
        this.category = category;

    }

    public void softDelete() {
        if (amountAvailable < amount)
            throw new IllegalArgumentException("There are still books on loan");

        this.available = false;
        this.active = false;
    }
}
