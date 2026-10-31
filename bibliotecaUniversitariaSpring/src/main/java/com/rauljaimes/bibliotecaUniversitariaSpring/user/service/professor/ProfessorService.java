package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

public class ProfessorService implements IProfessorService{
    private final ProfessorRepository profeRepository;
    private final IUserMapper userMapper;

    public ProfessorService(ProfessorRepository profeRepository, IUserMapper userMapper) {
        this.profeRepository = profeRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<ProfessorResponse> createProfessor(ProfessorRequest professorRequest) {
        return Optional.empty();
    }

    @Override
    public List<ProfessorResponse> findAll() {
        return profeRepository.findAll().stream()
                .map(userMapper::professorEntityToResponse)
                .toList();
    }

    @Override
    public List<ProfessorResponse> findByDepartment(String department) {
        return profeRepository.findByDepartment(department).stream()
                .map(userMapper::professorEntityToResponse)
                .toList();
    }

    @Override
    public void loanBook(Long id) {
        Integer rowsAffect = profeRepository.returnBook(id);

        if(rowsAffect == 0){
            ProfessorEntity student = profeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            if(student.getLoanBooks() == 0)
                throw new IllegalArgumentException("You can no longer return any more books");
        }
    }

    @Override
    public void returnBook(Long id) {
        Integer rowsAffect = profeRepository.returnBook(id);

        if(rowsAffect == 0){
            ProfessorEntity student = profeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            if(student.getLoanBooks() == 0)
                throw new IllegalArgumentException("You can no longer return any more books");
        }
    }
}
