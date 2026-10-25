package com.rauljaimes.bibliotecaUniversitariaSpring.book.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.mapper.BookMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(IBookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Optional<BookResponse> createBook(BookRequest bookRequest) {
        BookEntity book = BookEntity.builder()
                .title(bookRequest.title())
                .author(bookRequest.author())
                .yearPublication(bookRequest.yearPublication())
                .available(true)
                .amount(bookRequest.amount())
                .amountAvailable(bookRequest.amount())
                .isbn(bookRequest.isbn())
                .category(bookRequest.category())
                .active(true)
                .build();

        return Optional.of(bookRepository.save(book)).map(bookMapper::entityToResponse);
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
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(bookMapper::entityToResponse)
                .findFirst();
    }

    @Override
    public Optional<BookResponse> findByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::entityToResponse);
    }

    @Override
    public List<BookResponse> findByYear(Integer year) {
        return bookRepository.findByYearPublication(year).stream()
                .map(bookMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<BookResponse> findByAuthor(String author) {
        return bookRepository.findByAuthor(author).stream()
                .map(bookMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<BookResponse> findByGenre(String genre) {
        return bookRepository.findByCategory(genre).stream()
                .map(bookMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::entityToResponse)
                .toList();
    }

    @Transactional
    @Override
    public void sotfDelete(Long id) {
        Integer rowsAffect = bookRepository.softDelete(id);

        if (rowsAffect == 0) {
            BookEntity book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        }
    }

    @Transactional
    @Override
    public Optional<BookResponse> updateBook(BookRequest bookRequest, Long id) {
        BookEntity bookEntity = bookRepository.getReferenceById(id);

        bookEntity.setTitle(bookRequest.title());
        bookEntity.setAuthor(bookRequest.author());
        bookEntity.setYearPublication(bookRequest.yearPublication());
        bookEntity.setAmount(bookRequest.amount());
        bookEntity.setIsbn(bookRequest.isbn());
        bookEntity.setCategory(bookRequest.category());

        return Optional.of(bookRepository.save(bookEntity))
                .map(bookMapper::entityToResponse);
    }

    @Transactional
    @Override
    public void returnBook(Long id) {
        Integer rowsAffect = bookRepository.returnBook(id);

        if (rowsAffect == 0) {
            BookEntity book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));

            if (Objects.equals(book.getAmountAvailable(), book.getAmount())) {
                throw new IllegalArgumentException("You can't return that book");
            }
        }
    }

    @Transactional
    @Override
    public void loanBook(Long id) {
        Integer rowsAffect = bookRepository.loanBook(id);

        if (rowsAffect == 0) {
            BookEntity book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));

            if (Objects.equals(book.getAmountAvailable(), book.getAmount())) {
                throw new IllegalArgumentException("book out of print");
            }
        }
    }
}
