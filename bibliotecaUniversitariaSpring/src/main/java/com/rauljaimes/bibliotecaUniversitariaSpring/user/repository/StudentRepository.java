package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findBySemester(String semester);

    List<StudentEntity> findByDegree(String degree);

    Optional<StudentEntity> findBySemesterAndDegree(String semester, String degree);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE users
            SET loan_books = loan_books + 1
            WHERE id = ?
                        AND loan_books < 5
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
