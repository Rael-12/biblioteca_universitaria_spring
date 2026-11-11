package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final IUserMapper userMapper;
    private final StudentEntity studentEntity;

    public StudentService(StudentRepository studentRepository, IUserMapper userMapper, StudentEntity studentEntity) {
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
        this.studentEntity = studentEntity;
    }

    @Transactional
    @Override
    public StudentResponse createStudent(StudentRequest sr) {
        StudentEntity student = studentEntity.createStudent(
                sr.firstName(), sr.lastName(), sr.email(),
                String.valueOf(sr.phoneNumber()), sr.birthdate(), sr.semester(), sr.degree());

        return userMapper.studentEntityToResponse(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> findBySemester(String semester) {
        return studentRepository.findBySemester(semester).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> findByDegree(String degree) {
        return studentRepository.findByDegree(degree).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> findBySemesterAndDegree(String semester, String degree) {
        return studentRepository.findBySemesterAndDegree(semester, degree).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
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
