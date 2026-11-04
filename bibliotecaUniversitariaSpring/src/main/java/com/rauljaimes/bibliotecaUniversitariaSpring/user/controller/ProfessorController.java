package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor.IProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final IProfessorService profService;

    public ProfessorController(IProfessorService profService) {
        this.profService = profService;
    }

    @PostMapping()
    public ResponseEntity<ProfessorResponse> createProfessor(@Valid @RequestBody ProfessorRequest professorRequest){
        return ResponseEntity.of(profService.createProfessor(professorRequest));
    }

    @GetMapping()
    public List<ProfessorResponse> getAllProfessors(){
        return profService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(profService.findById(id));
    }

    @GetMapping("/{department}")
    public List<ProfessorResponse> findByDepartment(@PathVariable String department){
        return profService.findByDepartment(department);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id){
        profService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/returBook/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id){
        profService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
