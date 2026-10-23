package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.mapper.BookMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService implements IBookService{
    private final IBookRepository bookRepository;
    private final BookMapper  bookMapper;

    public BookService(IBookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Optional<BookResponse> createBook(BookRequest bookRequest) {
        return Optional.empty();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<BookResponse> findById(Long id) {
        return bookRepository.findById(id).map(bookMapper::entityToResponse);
    }

    @Override
    public Optional<BookResponse> fingByTitle(String title) {
        return bookRepository.findByTitle(title.toLowerCase()).stream()
                .map(bookMapper::entityToResponse)
                .findFirst();
    }

    @Override
    public Optional<BookResponse> findByISBN(String isbn) {
        return Optional.ofNullable(bookRepository.findByISBN().stream().map(b -> bookMapper.entityToResponse(b)));
    }

    @Override
    public List<BookResponse> findByYear(Integer year) {
        return List.of();
    }

    @Override
    public List<BookResponse> findByAuthor(String author) {
        return List.of();
    }

    @Override
    public List<BookResponse> findByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::entityToResponse)
                .toList();
    }

    @Override
    public void sotfDelete(Long id) {

    }

    @Override
    public Optional<BookResponse> updateBook(BookRequest bookRequest) {
        return Optional.empty();
    }

    @Override
    public void returnBook(BookRequest bookRequest) {

    }

    @Override
    public void loanBook(BookRequest bookRequest) {

    }
}
