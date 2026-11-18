package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service.IBookCategoryServ;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/${api.version}/bookCategory")
public class BookCategoryController {
    private final IBookCategoryServ bookCategoryServ;

    public BookCategoryController(IBookCategoryServ bookCategoryServ) {
        this.bookCategoryServ = bookCategoryServ;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<BookCategoryResponse>> findAll(@PageableDefault(size = 20, sort = "categoryName",
            direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(bookCategoryServ.findAll(pageable));
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
