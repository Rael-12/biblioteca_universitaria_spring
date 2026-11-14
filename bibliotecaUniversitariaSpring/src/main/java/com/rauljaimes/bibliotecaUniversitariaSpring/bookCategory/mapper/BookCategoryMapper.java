package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.mapper;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.dto.BookCategoryResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity.BookCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookCategoryMapper {
    BookCategoryResponse categoryEntityToResponse(BookCategoryEntity bookCategory);
}
