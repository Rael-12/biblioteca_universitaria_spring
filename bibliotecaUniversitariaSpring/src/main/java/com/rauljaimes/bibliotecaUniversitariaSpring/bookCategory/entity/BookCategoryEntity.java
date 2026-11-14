package com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.entity;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.bookCategory.exceptions.BookCategoryInactiveException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book_category")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(length = 40, nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "category")
    private List<BookEntity> books;

    public static BookCategoryEntity createCategory(String categoryName) {
        return BookCategoryEntity.builder()
                .categoryName(categoryName.toLowerCase())
                .active(true)
                .build();
    }

    public void softDelete(){
        if(!this.active)
            throw new BookCategoryInactiveException("The category is not active");
        this.active = false;
    }

    public void updateCategory(String categoryName) {
        if(!this.isActive())
            throw new BookCategoryInactiveException("The book category is inactive");
        this.categoryName = categoryName.toLowerCase();
    }
}
