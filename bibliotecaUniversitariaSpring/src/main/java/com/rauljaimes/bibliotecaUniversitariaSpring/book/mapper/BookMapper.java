package com.rauljaimes.bibliotecaUniversitariaSpring.book.mapper;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.dto.BookResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponse entityToResponse(BookEntity  bookEntity);

    BookEntity responseToEntity(BookResponse bookResponse);
}
