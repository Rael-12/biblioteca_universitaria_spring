package com.rauljaimes.bibliotecaUniversitariaSpring.book.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.service.IBookService;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/${api.version}/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookResponse> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> findByIsbn(@PathVariable String isbn) {
        return bookService.findByISBN(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<PageResponse<BookResponse>> findAll(@PageableDefault(
            size = 20,
            sort = "title",
            direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/year/{id}")
    public ResponseEntity<PageResponse<BookResponse>> findByYear(@PathVariable Integer id,
                                                                 @PageableDefault(size = 20,
                                                                         sort = "title",
                                                                         direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(bookService.findByYear(id, pageable));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<PageResponse<BookResponse>> findByAuthor(@PathVariable String author,
                                                                   @PageableDefault(
                                                                           size = 20,
                                                                           sort = "title",
                                                                           direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(bookService.findByAuthor(author, pageable));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<PageResponse<BookResponse>> findByGenre(@PathVariable String genre,
                                                                  @PageableDefault(
                                                                          size = 20,
                                                                          sort = "title",
                                                                          direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(bookService.findByGenre(genre, pageable));
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id) {
        bookService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        bookService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/softDelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        bookService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody
                                                   BookRequest bookRequest) {
        BookResponse book = bookService.createBook(bookRequest);

        return ResponseEntity.ok(book);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody
                                                   BookRequest bookRequest, @PathVariable Long id) {
        Optional<BookResponse> updated = bookService.updateBook(bookRequest, id);

        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

}
