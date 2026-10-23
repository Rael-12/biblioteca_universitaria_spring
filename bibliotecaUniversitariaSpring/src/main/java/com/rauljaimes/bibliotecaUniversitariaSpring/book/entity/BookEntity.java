package com.rauljaimes.bibliotecaUniversitariaSpring.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title",  nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "year_publication", nullable = false)
    private Integer yearPublication;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "amount_available",  nullable = false)
    private Integer amountAvailable;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "category", nullable = false)
   //@Enumerated
    private String category;

    @Column(name = "active",  nullable = false)
    private boolean active;
}
