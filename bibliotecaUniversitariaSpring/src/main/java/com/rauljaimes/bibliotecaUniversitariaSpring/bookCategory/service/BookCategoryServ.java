package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity.BookCategoryEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions.BookCategoryNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.mapper.BookCategoryMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.repository.BookCategoryRepo;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookCategoryServ implements IBookCategoryServ {
    private final BookCategoryRepo bookCategoryRepo;
    private final BookCategoryMapper bookCategoryMapper;
    private final PageMapper pageMapper;

    public BookCategoryServ(BookCategoryRepo bookCategoryRepo, BookCategoryMapper bookCategoryMapper, PageMapper pageMapper) {
        this.bookCategoryRepo = bookCategoryRepo;
        this.bookCategoryMapper = bookCategoryMapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PageResponse<BookCategoryResponse> findAll(Pageable pageable) {
        Page<BookCategoryResponse> categories = bookCategoryRepo.findAll(pageable)
                .map(bookCategoryMapper::categoryEntityToResponse);

        return pageMapper.toPageResponse(categories);
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
