package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {

   Optional<BookEntity> findByTitleContainingIgnoreCase(String title);

   Page<BookEntity> findByAuthor(String author, Pageable pageable);

   Optional<BookEntity> findByIsbn(String isbn);

   Page<BookEntity> findByYearPublication(Integer yearPublication, Pageable pageable);

   //boolean isActive(Long id);

   Page<BookEntity> findByCategory(String category, Pageable pageable);
}
