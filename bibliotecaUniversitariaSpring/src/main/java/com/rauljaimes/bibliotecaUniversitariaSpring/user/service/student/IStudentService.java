package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IStudentService {

    Optional<StudentResponse> createStudent(StudentRequest studentRequest);

    List<StudentResponse> findAll();

    List<StudentResponse> findBySemester(String semester);

    List<StudentResponse> findByDegree(String degree);

    List<StudentResponse> findBySemesterAndDegree(String semester, String degree);

    void loanBook(Long id);

    void returnBook(Long id);
}
