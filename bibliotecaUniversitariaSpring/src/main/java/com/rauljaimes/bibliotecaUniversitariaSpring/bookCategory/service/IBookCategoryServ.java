package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;

import java.util.List;
import java.util.Optional;

public interface IBookCategoryServ {

    List<BookCategoryResponse> findAll();

    Optional<BookCategoryResponse> findByCategoryName(BookCategoryRequest bookCategory);

    void softDelete(Long id);

    Optional<BookCategoryResponse> findById(Long id);

    BookCategoryResponse createCategory(BookCategoryRequest bookCategory);

    Optional<BookCategoryResponse> updateCategory(BookCategoryRequest bookCategory, Long id);
}
