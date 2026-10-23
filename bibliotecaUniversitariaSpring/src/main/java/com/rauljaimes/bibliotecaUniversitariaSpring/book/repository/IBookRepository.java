package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {

    @Transactional
    @Modifying
    @Query("""
        UPDATE BookEntity b
        SET b.active = 0
        WHERE b.bookId = :id
        """)
    void softDelete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE BookEntity b
            SET b.amount_available = b.amount_available + 1
            WHERE b.book_id = :id
            """)
    void loanBook(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE BookEntity b
            SET b.amount_available = b.amount_available + 1
            WHERE b.book_id = :id
            """)
    void returnBook(@Param("id") Long id);


   Optional<BookEntity> findByTitleContainingIgnoreCase(String title);

   Optional<BookEntity> findByAuthor(String author);

   Optional<BookEntity> findByIsbn(String isbn);

   List<BookEntity> findByYearPublication(Integer yearPublication);

   boolean isActive(Long id);

   List<BookEntity> findByCategory(String category);
}
