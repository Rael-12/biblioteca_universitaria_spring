package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
    @Query("""
        UPDATE books
        SET active = 0
        WHERE bookk_id = ?
        """)
    void softDelete(Long id);
}
