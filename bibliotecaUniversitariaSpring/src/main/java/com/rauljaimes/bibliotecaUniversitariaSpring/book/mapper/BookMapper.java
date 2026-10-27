package com.rauljaimes.bibliotecaUniversitariaSpring.book.mapper;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse entityToResponse(BookEntity  bookEntity);

    BookEntity responseToEntity(BookResponse bookResponse);
}
