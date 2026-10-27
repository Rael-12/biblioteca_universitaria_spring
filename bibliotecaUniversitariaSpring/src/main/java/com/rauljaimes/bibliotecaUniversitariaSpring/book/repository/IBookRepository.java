package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {

    @Transactional
    @Modifying
    @Query("""
        UPDATE BookEntity b
        SET b.active = false
        WHERE b.id = :id
        """)
    Integer softDelete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE BookEntity b
            SET b.amountAvailable = b.amountAvailable - 1
            WHERE b.id = :id
                        AND b.amountAvailable > 0
            """)
    Integer loanBook(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
            UPDATE BookEntity b
            SET b.amountAvailable = b.amountAvailable + 1
            WHERE b.id = :id
                        AND b.amountAvailable < b.amount
            """)
    Integer returnBook(@Param("id") Long id);


   Optional<BookEntity> findByTitleContainingIgnoreCase(String title);

   Optional<BookEntity> findByAuthor(String author);

   Optional<BookEntity> findByIsbn(String isbn);

   List<BookEntity> findByYearPublication(Integer yearPublication);

   //boolean isActive(Long id);

   List<BookEntity> findByCategory(String category);
}
