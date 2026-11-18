package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBookService {

    BookResponse createBook(BookRequest br);

    void deleteBook(Long id);

    Optional<BookResponse> findById(Long id);

    Optional<BookResponse> findByTitle(String name);

    Optional<BookResponse> findByISBN(String isbn);

    PageResponse<BookResponse> findByYear(Integer year, Pageable pageable);

    PageResponse<BookResponse> findByAuthor(String author, Pageable pageable);

    PageResponse<BookResponse> findByGenre(String genre, Pageable pageable);

    PageResponse<BookResponse> findAll(Pageable pageable);

    void softDelete(Long id);

    Optional<BookResponse> updateBook(BookRequest bookRequest, Long id);

    void returnBook(Long id);

    void loanBook(Long id);
}
