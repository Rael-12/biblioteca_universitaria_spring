package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity.BookCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookCategoryRepo extends JpaRepository<BookCategoryEntity, Long> {

    Optional<BookCategoryEntity> findByCategoryName(String categoryName);
}
