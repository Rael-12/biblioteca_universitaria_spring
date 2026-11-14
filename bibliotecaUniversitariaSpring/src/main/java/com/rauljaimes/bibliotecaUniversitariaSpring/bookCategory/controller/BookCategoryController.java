package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service.IBookCategoryServ;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/bookCategory")
public class BookCategoryController {
    private final IBookCategoryServ bookCategoryServ;

    public BookCategoryController(IBookCategoryServ bookCategoryServ) {
        this.bookCategoryServ = bookCategoryServ;
    }

    @GetMapping()
    public List<BookCategoryResponse> findAll(){
        return bookCategoryServ.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(bookCategoryServ.findById(id));
    }

    @GetMapping("/category/")
    public ResponseEntity<BookCategoryResponse> findCategoryName(@Valid @RequestBody BookCategoryRequest bookCategory){
        return ResponseEntity.of(bookCategoryServ.findByCategoryName(bookCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id){
        bookCategoryServ.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public BookCategoryResponse createCategory(@Valid @RequestBody BookCategoryRequest bookCategory){
        return bookCategoryServ.createCategory(bookCategory);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookCategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody BookCategoryRequest bookCategory){
        return ResponseEntity.of(bookCategoryServ.updateCategory(bookCategory, id));
    }
}
