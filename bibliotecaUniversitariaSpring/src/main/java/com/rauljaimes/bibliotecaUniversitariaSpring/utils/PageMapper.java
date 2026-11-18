package com.rauljaimes.bibliotecaUniversitariaSpring.utils;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper<T> {

    PageResponse<T> toPageResponse(Page<T> page);
}
