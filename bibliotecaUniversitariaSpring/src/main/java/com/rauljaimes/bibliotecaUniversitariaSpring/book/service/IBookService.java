package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    Optional<BookEntity> createBook(BookEntity bookEntity);

    void deleteBook(Long id);

    Optional<BookEntity> findById(Long id);

    Optional<BookEntity> fingByName(String name);

    Optional<BookEntity> findByISBN(String isbn);

    List<BookEntity> findByYear(Integer year);

    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByGenre(String genre);

    List<BookEntity> findAll();

    void sotfDelete(Long id);

    Optional<BookEntity> updateBook(BookEntity bookEntity);

    void returnBook(BookEntity bookEntity);

    void loanBook(BookEntity bookEntity);
}
