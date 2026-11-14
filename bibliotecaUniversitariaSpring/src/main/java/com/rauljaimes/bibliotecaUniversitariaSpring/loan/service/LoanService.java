package com.rauljaimes.bibliotecaUniversitariaSpring.loan.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.exceptions.BookNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.exceptions.LoanNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.mapper.ILoanMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.repository.ILoansRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService implements ILoanService {

    private final ILoansRepository loansRepository;
    private final ILoanMapper loanMapper;
    private final UserRepository userRepository;
    private final IBookRepository bookRepository;

    public LoanService(ILoansRepository loansRepository, ILoanMapper loanMapper,
                       UserRepository userRepository, IBookRepository bookRepository) {
        this.loansRepository = loansRepository;
        this.loanMapper = loanMapper;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public LoanResponse createLoan(LoanRequest loanRequest) {
        BookEntity book = bookRepository.findById(loanRequest.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        UserEntity user = userRepository.findById(loanRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!book.isActive())
            throw new BookNotFoundException("Book not available");

        if (!user.isActive())
            throw new UserNotFoundException("User not available");

        LoanEntity loan = LoanEntity.createLoan(user, book);

        book.loanBook();
        user.loanBook();

        return loanMapper.loanEntityToResponse(loansRepository.save(loan));
    }


    @Transactional
    @Override
    public void returnBook(Long id) {
        LoanEntity loan = loansRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        loan.returnBook();
        loansRepository.save(loan);
    }

    @Transactional
    @Override
    public LoanResponse renewBook(Long id) {
        LoanEntity loan = loansRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        loan.renewLoan();
        return loanMapper.loanEntityToResponse(loansRepository.save(loan));
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanResponse> findAll() {
        return loansRepository.findAll().stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanResponse> findByBook(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        return loansRepository.findByBook(book).stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanResponse> findByUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return loansRepository.findByUser(user).stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<LoanResponse> findById(Long id) {
        return loansRepository.findById(id)
                .map(loanMapper::loanEntityToResponse);
    }
}