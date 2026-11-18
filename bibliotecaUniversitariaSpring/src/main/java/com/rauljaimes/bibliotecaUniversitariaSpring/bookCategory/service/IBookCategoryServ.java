package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookCategoryServ {

    PageResponse<BookCategoryResponse> findAll(Pageable pageable);

    Optional<BookCategoryResponse> findByCategoryName(BookCategoryRequest bookCategory);

    void softDelete(Long id);

    Optional<BookCategoryResponse> findById(Long id);

    BookCategoryResponse createCategory(BookCategoryRequest bookCategory);

    Optional<BookCategoryResponse> updateCategory(BookCategoryRequest bookCategory, Long id);
}
