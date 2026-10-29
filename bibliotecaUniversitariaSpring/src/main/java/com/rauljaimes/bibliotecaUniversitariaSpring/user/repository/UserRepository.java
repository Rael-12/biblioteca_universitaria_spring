package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String lastName);

    Optional<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = """
            UPDATE users
            SET users.active = 0
            WHERE id = ?
            """, nativeQuery = true)
    Integer softDelete(Long id);

    @Query(value = """
                SELECT users.loan_books
                FROM users
                WHERE id = ?
            """, nativeQuery = true)
    Integer loanBooks(Long id);

    @Query(value = """
            UPDATE users
            SET users.loan_books = loan_books + 1
            WHERE users.id = ?
            """, nativeQuery = true)
    @Modifying
    @Transactional
    Integer loanBook(Long id);

    @Query(value = """
            UPDATE users
            SET users.loan_books = loan_books - 1
            WHERE users.id = ?
                        AND users.loan_books > 0
            """, nativeQuery = true)
    @Modifying
    @Transactional
    Integer returnBook(Long id);

}
