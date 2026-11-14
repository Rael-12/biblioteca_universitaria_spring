package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity.BookCategoryEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions.BookCategoryNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.mapper.BookCategoryMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.repository.BookCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryServ implements IBookCategoryServ {
    private final BookCategoryRepo bookCategoryRepo;
    private final BookCategoryMapper bookCategoryMapper;

    public BookCategoryServ(BookCategoryRepo bookCategoryRepo, BookCategoryMapper bookCategoryMapper) {
        this.bookCategoryRepo = bookCategoryRepo;
        this.bookCategoryMapper = bookCategoryMapper;
    }

    @Override
    public List<BookCategoryResponse> findAll() {
        return bookCategoryRepo.findAll().stream()
                .filter(BookCategoryEntity::isActive)
                .map(bookCategoryMapper::categoryEntityToResponse)
                .toList();
    }

    @Override
    public Optional<BookCategoryResponse> findByCategoryName(BookCategoryRequest categoryRequest) {
        return bookCategoryRepo.findByCategoryName(categoryRequest.categoryName().toLowerCase())
                .map(bookCategoryMapper::categoryEntityToResponse);
    }

    @Override
    public void softDelete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");

        BookCategoryEntity category = bookCategoryRepo.findById(id)
                .orElseThrow(() -> new BookCategoryNotFoundException("Book category not found"));

        category.softDelete();
        bookCategoryRepo.save(category);
    }

    @Override
    public Optional<BookCategoryResponse> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");

        return bookCategoryRepo.findById(id)
                .map(bookCategoryMapper::categoryEntityToResponse);
    }

    @Override
    public BookCategoryResponse createCategory(BookCategoryRequest bookCategory) {
        BookCategoryEntity bookCat = BookCategoryEntity.createCategory(bookCategory.categoryName());
        return bookCategoryMapper.categoryEntityToResponse(bookCategoryRepo.save(bookCat));
    }

    @Override
    public Optional<BookCategoryResponse> updateCategory(BookCategoryRequest bookCategory, Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");

        BookCategoryEntity bookCat = bookCategoryRepo.findById(id)
                .orElseThrow(() -> new BookCategoryNotFoundException("Book category not found"));
        bookCat.updateCategory(bookCategory.categoryName());

        return Optional.of(bookCategoryRepo.save(bookCat))
                .map(bookCategoryMapper::categoryEntityToResponse);
    }
}
