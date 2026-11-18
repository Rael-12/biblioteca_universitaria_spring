package com.rauljaimes.bibliotecaUniversitariaSpring.loan.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ILoanService {

    LoanResponse createLoan(LoanRequest loanRequest);

    void returnBook(Long id);

    LoanResponse renewBook(Long id);

    PageResponse<LoanResponse> findAll(Pageable pageable);

    PageResponse<LoanResponse> findByBook(Long id, Pageable pageable);

    PageResponse<LoanResponse> findByUser(Long id, Pageable pageable);

    Optional<LoanResponse> findById(Long id);

}
