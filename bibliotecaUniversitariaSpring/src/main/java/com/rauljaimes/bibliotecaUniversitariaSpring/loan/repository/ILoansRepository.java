package com.rauljaimes.bibliotecaUniversitariaSpring.loan.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILoansRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByUser(UserEntity user);

    List<LoanEntity> findByBook(BookEntity book);
}
