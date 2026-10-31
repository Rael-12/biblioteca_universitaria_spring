package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long>{
    List<ProfessorEntity> findByDepartment(String department);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE users
            SET loan_books = loan_books + 1
            WHERE id = ?
                        AND loan_books < 10
            """, nativeQuery = true)
    Integer loanBook(Long id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE users
            SET loan_books = loan_books - 1
            WHERE id = ?
                        AND loan_books > 0
            """, nativeQuery = true)
    Integer returnBook(Long id);
}
