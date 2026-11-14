package com.rauljaimes.bibliotecaUniversitariaSpring.loan.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;

import java.util.List;
import java.util.Optional;

public interface ILoanService {

    LoanResponse createLoan(LoanRequest loanRequest);

    void returnBook(Long id);

    LoanResponse renewBook(Long id);

    List<LoanResponse> findAll();

    List<LoanResponse> findByBook(Long id);

    List<LoanResponse> findByUser(Long id);

    Optional<LoanResponse> findById(Long id);

}
