package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor.IProfessorService;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/professors")
public class ProfessorController {
    private final IProfessorService profService;

    public ProfessorController(IProfessorService profService) {
        this.profService = profService;
    }

    @PostMapping()
    public ResponseEntity<ProfessorResponse> createProfessor(@Valid @RequestBody ProfessorRequest professorRequest) {
        return ResponseEntity.ok(profService.createProfessor(professorRequest));
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ProfessorResponse>> getAllProfessors(@PageableDefault(size = 20,
            sort = "firstName",
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.ok(profService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.of(profService.findById(id));
    }

    @GetMapping("/{department}")
    public ResponseEntity<PageResponse<ProfessorResponse>> findByDepartment(@PathVariable String department, @PageableDefault(size = 20,
            sort = "firstName", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(profService.findByDepartment(department, pageable));
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id) {
        profService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}