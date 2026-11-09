package com.rauljaimes.bibliotecaUniversitariaSpring.loan.service;

import com.rauljaimes.bibliotecaUniversitariaSpring.book.entity.BookEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.exceptions.BookNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.repository.IBookRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.book.service.IBookService;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.mapper.ILoanMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.repository.ILoansRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor.IProfessorService;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student.IStudentService;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService implements ILoanService {

    private final ILoansRepository loansRepository;
    private final ILoanMapper loanMapper;
    private final UserRepository userRepository;
    private final IBookRepository bookRepository;
    private final IBookService bookService;
    private final IUserService userService;
    private final IStudentService studentService;
    private final IProfessorService profService;

    public LoanService(ILoansRepository loansRepository, ILoanMapper loanMapper, UserRepository userRepository, IBookRepository bookRepository, IBookService bookService, IUserService userService, IStudentService studentService, IProfessorService profService) {
        this.loansRepository = loansRepository;
        this.loanMapper = loanMapper;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.studentService = studentService;
        this.profService = profService;
    }

    @Transactional
    @Override
    public Optional<LoanResponse> createLoan(LoanRequest loanRequest) {
        BookEntity book = bookRepository.findById(loanRequest.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        UserEntity user = userRepository.findById(loanRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        LoanEntity loan = LoanEntity.builder()
                .user(user)
                .book(book)
                .active(true)
                .renewedCount(0L)
                .status("PRESTADO")
                .build();

        switch (user) {
            case StudentEntity student -> {
                studentService.loanBook(student.getId());
                loan.setDueDate(LocalDate.now().plusDays(15));
            }

            case ProfessorEntity profe -> {
                profService.loanBook(profe.getId());
                loan.setDueDate(LocalDate.now().plusDays(30));
            }

            default -> throw new UserNotFoundException("User not found");
        }

        bookService.loanBook(book.getId());

        return Optional.of(loansRepository.save(loan))
                .map(loanMapper::loanEntityToResponse);
    }

    @Override
    public Long softDelete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");

        if (!loansRepository.existsById(id))
            throw new IllegalArgumentException("Loan not found");

        Long rowsAffect = loansRepository.softDelete(id);

        if (rowsAffect == 0)
            throw new IllegalArgumentException("The deletion could not be performed");

        return rowsAffect;
    }

    @Override
    public void hardDelete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");

        if (!loansRepository.existsById(id))
            throw new IllegalArgumentException("Loan not found");

        loansRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void returnBook(LoanRequest loanRequest) {
        BookEntity book = bookRepository.findById(loanRequest.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        UserEntity user = userRepository.findById(loanRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        LoanEntity loan = loansRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        bookService.returnBook(book.getId());
        userService.returnBook(user.getId());

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("DEVUELTO");

        loansRepository.save(loan);
    }

    @Override
    public String changeStatus(Long loanId, String status) {
        Long rowsAffect = loansRepository.changeStaus(status, loanId);

        if (rowsAffect == 0 && !loansRepository.existsById(loanId))
            throw new IllegalArgumentException("Loan not found");

        return status;
    }

    @Override
    public Optional<LoanResponse> renewBook(LoanRequest loanRequest) {
        BookEntity book = bookRepository.findById(loanRequest.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        UserEntity user = userRepository.findById(loanRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        LoanEntity loan = loansRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        switch (user) {
            case StudentEntity student -> loan.setDueDate(LocalDate.now().plusDays(15));

            case ProfessorEntity profe -> loan.setDueDate(LocalDate.now().plusDays(30));

            default -> throw new UserNotFoundException("User not found");
        }

        return Optional.of(loansRepository.save(loan))
                .map(loanMapper::loanEntityToResponse);
    }

    @Override
    public List<LoanResponse> findAll() {
        return loansRepository.findAll().stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> findByBook(Long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);

        if (book == null)
            throw new BookNotFoundException("Book not found");

        return loansRepository.findByBook(book).stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> findByUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("User not found");

        return loansRepository.findByUser(user.get()).stream()
                .map(loanMapper::loanEntityToResponse)
                .toList();
    }

    @Override
    public Optional<LoanResponse> findById(Long id) {
        return loansRepository.findById(id)
                .map(loanMapper::loanEntityToResponse);
    }
}
