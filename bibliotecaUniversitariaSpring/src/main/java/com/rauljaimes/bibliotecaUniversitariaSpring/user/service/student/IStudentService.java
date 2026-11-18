package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IStudentService {

    StudentResponse createStudent(StudentRequest sr);

    PageResponse<StudentResponse> findAll(Pageable pageable);

    PageResponse<StudentResponse> findBySemester(String semester, Pageable pageable);

    PageResponse<StudentResponse> findByDegree(String degree, Pageable pageable);

    PageResponse<StudentResponse> findBySemesterAndDegree(String semester, String degree, Pageable  pageable);

    Optional<StudentResponse> findById(Long id);

    void loanBook(Long id);
}
