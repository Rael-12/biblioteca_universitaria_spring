package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;

import java.util.List;
import java.util.Optional;

public class BookService implements IBookService{
    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<BookEntity> createBook(BookEntity bookEntity) {
        return Optional.empty();
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> fingByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByISBN(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<BookEntity> findByYear(Integer year) {
        return List.of();
    }

    @Override
    public List<BookEntity> findByAuthor(String author) {
        return List.of();
    }

    @Override
    public List<BookEntity> findByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<BookEntity> findAll() {
        return List.of();
    }

    @Override
    public void sotfDelete(Long id) {

    }

    @Override
    public Optional<BookEntity> updateBook(BookEntity bookEntity) {
        return Optional.empty();
    }

    @Override
    public void returnBook(BookEntity bookEntity) {

    }

    @Override
    public void loanBook(BookEntity bookEntity) {

    }
}
