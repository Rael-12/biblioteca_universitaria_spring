package com.rauljaimes.bibliotecaUniversitariaSpring.utils;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        Long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
}
