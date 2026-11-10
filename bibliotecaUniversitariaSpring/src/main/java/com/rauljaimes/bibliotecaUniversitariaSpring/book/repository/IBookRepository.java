package com.rauljaimes.bibliotecaUniversitariaSpring.book.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {

   Optional<BookEntity> findByTitleContainingIgnoreCase(String title);

   Optional<BookEntity> findByAuthor(String author);

   Optional<BookEntity> findByIsbn(String isbn);

   List<BookEntity> findByYearPublication(Integer yearPublication);

   //boolean isActive(Long id);

   List<BookEntity> findByCategory(String category);
}
