package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.student.IStudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/satudents")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest){
        return ResponseEntity.of(studentService.createStudent(studentRequest));
    }

    @GetMapping()
    public List<StudentResponse> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(studentService.findById(id));
    }

    @GetMapping("/{semester}")
        public List<StudentResponse> getStudentsBySemester(@PathVariable String semester){
        return studentService.findBySemester(semester);
    }

    @GetMapping("/{degree}")
    public List<StudentResponse> getStudentsByDegree(@PathVariable String degree){
        return studentService.findByDegree(degree);
    }

    @GetMapping("/{semester}/{degree}")
    public List<StudentResponse> getBySemesterAndDegree(@PathVariable String semester, @PathVariable String degree){
        return studentService.findBySemesterAndDegree(semester, degree);
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Void> loanBook(@PathVariable Long id){
        studentService.loanBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
