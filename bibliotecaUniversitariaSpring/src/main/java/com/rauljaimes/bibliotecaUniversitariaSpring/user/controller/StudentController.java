package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student.IStudentService;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/satudents")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.createStudent(studentRequest));
    }

    @GetMapping()
    public ResponseEntity<PageResponse<StudentResponse>> getAllStudents(@PageableDefault(size = 20, sort = "firstName",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(studentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.of(studentService.findById(id));
    }

    @GetMapping("/{semester}")
    public ResponseEntity<PageResponse<StudentResponse>> getStudentsBySemester(@PathVariable String semester, @PageableDefault(size = 20, sort = "firstName",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(studentService.findBySemester(semester, pageable));
    }

    @GetMapping("/{degree}")
    public ResponseEntity<PageResponse<StudentResponse>> getStudentsByDegree(@PathVariable String degree, @PageableDefault
            (size = 20, sort = "firstName", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(studentService.findByDegree(degree, pageable));
    }

    @GetMapping("/{semester}/{degree}")
    public ResponseEntity<PageResponse<StudentResponse>> getBySemesterAndDegree(@PathVariable String semester,
                                                                                @PathVariable String degree,
                                                                                @PageableDefault(size = 20, sort = "firstName",
                                                                                        direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(studentService.findBySemesterAndDegree(semester, degree, pageable));
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id) {
        studentService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
