package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.StudentRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    private final IUserMapper userMapper;

    public StudentService(StudentRepository studentRepository, IUserMapper userMapper) {
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public Optional<StudentResponse> createStudent(StudentRequest studentRequest) {
        StudentEntity student = StudentEntity.builder()
                .firstName(studentRequest.firstName())
                .lastName(studentRequest.lastName())
                .email(studentRequest.email())
                .phoneNumber(String.valueOf(studentRequest.phoneNumber()))
                .birthdate(studentRequest.birthdate())
                .active(true)
                .loanBooks(0)
                .semester(studentRequest.semester())
                .degree(studentRequest.degree())
                .build();

        return Optional.of(studentRepository.save(student))
                .map(userMapper::studentEntityToResponse);
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> findBySemester(String semester) {
        return studentRepository.findBySemester(semester).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> findByDegree(String degree) {
        return studentRepository.findByDegree(degree).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> findBySemesterAndDegree(String semester, String degree) {
        return studentRepository.findBySemesterAndDegree(semester, degree).stream()
                .map(userMapper::studentEntityToResponse)
                .toList();
    }

    @Transactional
    @Override
    public void loanBook(Long id) {
        Integer rowsAffect = studentRepository.loanBook(id);

        if(rowsAffect == 0){
            StudentEntity student = studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            if(student.getLoanBooks() == 5)
                throw new IllegalArgumentException("No more books can be lent to the user");
        }
    }

    @Transactional
    @Override
    public void returnBook(Long id) {
        Integer rowsAffect = studentRepository.returnBook(id);

        if(rowsAffect == 0){
            StudentEntity student = studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            if(student.getLoanBooks() == 0)
                throw new IllegalArgumentException("You can no longer return any more books");
        }
    }
}
