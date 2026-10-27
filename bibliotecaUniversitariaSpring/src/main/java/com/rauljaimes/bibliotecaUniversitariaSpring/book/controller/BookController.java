package com.rauljaimes.bibliotecaUniversitariaSpring.book.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/books")
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
    public List<BookResponse> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/year/{id}")
    public List<BookResponse> findByYear(@PathVariable Integer id){
        return bookService.findByYear(id);
    }

    @GetMapping("/author/{author}")
    public List<BookResponse> findByAuthor(@PathVariable String author){
        return bookService.findByAuthor(author);
    }

    @GetMapping("/genre/{genre}")
    public List<BookResponse> findByGenre(@PathVariable String genre){
        return bookService.findByGenre(genre);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id){
        bookService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id){
        bookService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/softDelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id){
        bookService.sotfDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //todo aqui van los valores de los objetos con requestBody

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody
                                                       BookRequest bookRequest){
        Optional<BookResponse> book = bookService.createBook(bookRequest);

        return book.map(bookResponse -> ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookResponse)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody
                                                       BookRequest bookRequest, @PathVariable Long id){
        Optional<BookResponse> updated = bookService.updateBook(bookRequest, id);

        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

}
