package com.rauljaimes.bibliotecaUniversitariaSpring.loan.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoansRepository extends JpaRepository<LoanEntity, Long> {

    Page<LoanEntity> findByUser(UserEntity user, Pageable pageable);

    Page<LoanEntity> findByBook(BookEntity book, Pageable pageable);
}
