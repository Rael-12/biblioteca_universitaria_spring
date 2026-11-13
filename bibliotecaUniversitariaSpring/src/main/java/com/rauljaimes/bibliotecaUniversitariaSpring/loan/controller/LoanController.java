package com.rauljaimes.bibliotecaUniversitariaSpring.loan.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.service.ILoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/${api.version}/loans")
public class LoanController {
    private final ILoanService loanService;

    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public List<LoanResponse> findAll(){
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(loanService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public List<LoanResponse>  findByUser(@PathVariable Long userId){
        return loanService.findByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<LoanResponse> findByBook(@PathVariable Long bookId){
        return loanService.findByBook(bookId);
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