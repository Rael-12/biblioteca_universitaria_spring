package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findById( Long id);

    Optional<BookEntity> findByTitle(String title);

    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByCategory(String category);

    List<BookEntity> findByYearPublished(int yearPublished);

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findAll();

    List<BookEntity> findAllActive();

    List<BookEntity> findAllInactive();
}
