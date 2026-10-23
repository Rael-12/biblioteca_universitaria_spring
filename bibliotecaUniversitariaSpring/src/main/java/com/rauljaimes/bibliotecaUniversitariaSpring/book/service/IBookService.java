package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;

import java.util.List;
import java.util.Optional;

public interface IBookService {

     Optional<BookResponse> createBook(BookRequest bookRequest);

    void deleteBook(Long id);

    Optional<BookResponse> findById(Long id);

    Optional<BookResponse> fingByTitle(String name);

    Optional<BookResponse> findByISBN(String isbn);

    List<BookResponse> findByYear(Integer year);

    List<BookResponse> findByAuthor(String author);

    List<BookResponse> findByGenre(String genre);

    List<BookResponse> findAll();

    void sotfDelete(Long id);

    Optional<BookResponse> updateBook(BookRequest bookRequest);

    void returnBook(BookRequest bookRequest);

    void loanBook(BookRequest bookRequest);
}
