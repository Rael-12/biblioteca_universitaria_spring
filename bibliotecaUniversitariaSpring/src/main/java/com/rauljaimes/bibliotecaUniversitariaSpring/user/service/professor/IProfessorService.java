package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProfessorService {

    Optional<ProfessorResponse> createProfessor(ProfessorRequest professorRequest);

    List<ProfessorResponse> findAll();

    List<ProfessorResponse> findByDepartment(String department);

    void loanBook(Long id);

    void returnBook(Long id);
}
