package com.rauljaimes.bibliotecaUniversitariaSpring.loan.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ILoanService {

    Optional<LoanResponse> createLoan(LoanRequest loanRequest);

    Long softDelete(Long id);

    void hardDelete(Long id);

    void returnBook(LoanRequest loanRequest);

    String changeStatus(Long loanId, String status);

    Optional<LoanResponse> renewBook(LoanRequest loanRequest);

    List<LoanResponse> findAll();

    List<LoanResponse> findByBook(Long id);

    List<LoanResponse> findByUser(Long id);

    Optional<LoanResponse> findById(Long id);

}
