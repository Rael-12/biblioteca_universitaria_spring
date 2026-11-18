package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.professor;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.ProfessorRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService {
    private final ProfessorRepository profeRepository;
    private final IUserMapper userMapper;
    private final PageMapper pageMapper;

    public ProfessorService(ProfessorRepository profeRepository, IUserMapper userMapper, PageMapper pageMapper) {
        this.profeRepository = profeRepository;
        this.userMapper = userMapper;
        this.pageMapper = pageMapper;
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
    public PageResponse<ProfessorResponse> findAll(Pageable pageable) {
        Page<ProfessorResponse> professors = profeRepository.findAll(pageable)
                .map(userMapper::professorEntityToResponse);

        return pageMapper.toPageResponse(professors);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProfessorResponse> findById(Long id) {
        return profeRepository.findById(id)
                .map(userMapper::professorEntityToResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProfessorResponse> findByDepartment(String department, Pageable pageable) {
        Page<ProfessorResponse> professors = profeRepository.findByDepartment(department, pageable)
                .map(userMapper::professorEntityToResponse);

        return pageMapper.toPageResponse(professors);
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