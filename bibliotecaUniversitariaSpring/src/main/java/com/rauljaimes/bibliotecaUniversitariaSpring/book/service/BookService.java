package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.exceptions.BookNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.mapper.BookMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity.BookCategoryEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions.BookCategoryNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.repository.BookCategoryRepo;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookCategoryRepo bookCategoryRepo;
    private final PageMapper<BookResponse> pageMapper;

    public BookService(IBookRepository bookRepository, BookMapper bookMapper,
                       BookCategoryRepo bookCategoryRepo, PageMapper<BookResponse> pageMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookCategoryRepo = bookCategoryRepo;
        this.pageMapper = pageMapper;
    }

    @Transactional
    @Override
    public BookResponse createBook(BookRequest br) {
        BookCategoryEntity category = bookCategoryRepo.findById(br.categoryId())
                .orElseThrow(() -> new BookCategoryNotFoundException("Category not found"));

        BookEntity book = BookEntity.createBook(br.title(), br.author(), br.yearPublication(),
                br.amount(), br.isbn(), category);

        return bookMapper.entityToResponse(bookRepository.save(book));
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookResponse> findById(Long id) {
        return bookRepository.findById(id).map(bookMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookResponse> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(bookMapper::entityToResponse)
                .findFirst();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookResponse> findByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<BookResponse> findByYear(Integer year, Pageable pageable) {
         Page<BookResponse> books = bookRepository.findByYearPublication(year, pageable)
                 .map(bookMapper::entityToResponse);

         return pageMapper.toPageResponse(books);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<BookResponse> findByAuthor(String author, Pageable pageable) {
        Page<BookResponse> books = bookRepository.findByAuthor(author, pageable)
                .map(bookMapper::entityToResponse);
        return pageMapper.toPageResponse(books);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<BookResponse> findByGenre(String genre, Pageable pageable) {
        Page<BookResponse> books =  bookRepository.findByCategory(genre, pageable)
                .map(bookMapper::entityToResponse);
        return pageMapper.toPageResponse(books);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<BookResponse> findAll(Pageable pageable) {
        Page<BookResponse> books = bookRepository.findAll(pageable)
                .map(bookMapper::entityToResponse);
        return pageMapper.toPageResponse(books);
    }

    @Transactional
    @Override
    public void softDelete(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.softDelete();
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public Optional<BookResponse> updateBook(BookRequest br, Long id) {
        BookCategoryEntity category = bookCategoryRepo.findById(br.categoryId())
                .orElseThrow(() -> new BookCategoryNotFoundException("Category not found"));

        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.updateBook(br.title(), br.author(), br.yearPublication(),
                br.amount(), br.isbn(), category);

        return Optional.of(bookRepository.save(book))
                .map(bookMapper::entityToResponse);
    }

    @Transactional
    @Override
    public void returnBook(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.returnBook();
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void loanBook(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.loanBook();
        bookRepository.save(book);
    }
}