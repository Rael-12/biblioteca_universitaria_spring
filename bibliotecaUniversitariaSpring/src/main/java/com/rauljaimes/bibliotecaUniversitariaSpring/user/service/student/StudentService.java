package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.enums.Semester;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.StudentRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final IUserMapper userMapper;
    private final PageMapper pageMapper;

    public StudentService(StudentRepository studentRepository, IUserMapper userMapper, PageMapper pageMapper) {
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
        this.pageMapper = pageMapper;
    }

    @Transactional
    @Override
    public StudentResponse createStudent(StudentRequest sr) {
        StudentEntity student = StudentEntity.createStudent(
                sr.firstName(), sr.lastName(), sr.email(),
                String.valueOf(sr.phoneNumber()), sr.birthdate(), Semester.valueOf(sr.semester()), sr.degree());

        return userMapper.studentEntityToResponse(student);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<StudentResponse> findAll(Pageable pageable) {
        Page<StudentResponse> students = studentRepository.findAll(pageable)
                .map(userMapper::studentEntityToResponse);

        return pageMapper.toPageResponse(students);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<StudentResponse> findBySemester(String semester, Pageable pageable) {
        Page<StudentResponse> students = studentRepository.findBySemester(semester, pageable)
                .map(userMapper::studentEntityToResponse);

        return pageMapper.toPageResponse(students);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<StudentResponse> findByDegree(String degree, Pageable pageable) {
        Page<StudentResponse> students = studentRepository.findByDegree(degree, pageable)
                .map(userMapper::studentEntityToResponse);

        return pageMapper.toPageResponse(students);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<StudentResponse> findBySemesterAndDegree(String semester, String degree, Pageable pageable) {
        Page<StudentResponse> students = studentRepository.findBySemesterAndDegree(semester, degree, pageable)
                .map(userMapper::studentEntityToResponse);

        return pageMapper.toPageResponse(students);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<StudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(userMapper::studentEntityToResponse);
    }

    @Transactional
    @Override
    public void loanBook(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        student.loanBook();
        studentRepository.save(student);
    }
}
