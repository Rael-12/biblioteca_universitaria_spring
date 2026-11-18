package com.rauljaimes.bibliotecaUniversitariaSpring.loan.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.service.ILoanService;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/loans")
public class LoanController {
    private final ILoanService loanService;

    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<LoanResponse>> findAll(@PageableDefault(size = 20, sort = "dateLoan",
            direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(loanService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(loanService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageResponse<LoanResponse>>  findByUser(@PathVariable Long userId,
                                                                  @PageableDefault(size = 20, sort = "dateLoan",
                                                                          direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(loanService.findByUser(userId, pageable));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<PageResponse<LoanResponse>> findByBook(@PathVariable Long bookId,
                                                                 @PageableDefault(size = 20, sort = "dateLoan",
                                                                         direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(loanService.findByBook(bookId, pageable));
    }

    @PatchMapping("/renew/{id}")
    public ResponseEntity<LoanResponse> renewBook(@PathVariable Long id){
        return ResponseEntity.ok(loanService.renewBook(id));
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id){
        loanService.returnBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest loanRequest){
        return ResponseEntity.ok(loanService.createLoan(loanRequest));
    }

}