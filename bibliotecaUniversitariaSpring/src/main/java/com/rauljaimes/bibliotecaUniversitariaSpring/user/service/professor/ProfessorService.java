package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService {
    private final ProfessorRepository profeRepository;
    private final IUserMapper userMapper;

    public ProfessorService(ProfessorRepository profeRepository, IUserMapper userMapper) {
        this.profeRepository = profeRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public ProfessorResponse createProfessor(ProfessorRequest pr) {
        ProfessorEntity professor = ProfessorEntity.createProfessor(pr.firstName(), pr.lastName(), pr.email(),
                String.valueOf(pr.phoneNumber()), pr.birthdate(), pr.department());

        return userMapper.professorEntityToResponse(profeRepository.save(professor));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfessorResponse> findAll() {
        return profeRepository.findAll().stream()
                .map(userMapper::professorEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProfessorResponse> findById(Long id) {
        return profeRepository.findById(id)
                .map(userMapper::professorEntityToResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfessorResponse> findByDepartment(String department) {
        return profeRepository.findByDepartment(department).stream()
                .map(userMapper::professorEntityToResponse)
                .toList();
    }

    @Transactional
    @Override
    public void loanBook(Long id) {
        ProfessorEntity profe = profeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        profe.loanBook();
        profeRepository.save(profe);
    }
}