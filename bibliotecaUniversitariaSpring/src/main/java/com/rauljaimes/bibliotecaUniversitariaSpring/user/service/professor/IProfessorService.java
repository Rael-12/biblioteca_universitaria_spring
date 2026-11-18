package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProfessorService {

    ProfessorResponse createProfessor(ProfessorRequest pr);

    PageResponse<ProfessorResponse> findAll(Pageable pageable);

    Optional<ProfessorResponse> findById(Long id);

    PageResponse<ProfessorResponse> findByDepartment(String department, Pageable pageable);

    void loanBook(Long id);
}
