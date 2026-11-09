package com.rauljaimes.bibliotecaUniversitariaSpring.loan.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILoansRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByUser(UserEntity user);

    List<LoanEntity> findByBook(BookEntity book);

    Optional<LoanEntity> findByUserAndBook(UserEntity user, BookEntity book);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE loans
            SET loans.active = 0
            WHERE id = :id
            """, nativeQuery = true)
    Long softDelete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE loans
            SET loans.status = :status
            WHERE loans.id = :id
            """, nativeQuery = true)
    Long changeStaus(@Param("status") String status, @Param("id") Long id);
}
