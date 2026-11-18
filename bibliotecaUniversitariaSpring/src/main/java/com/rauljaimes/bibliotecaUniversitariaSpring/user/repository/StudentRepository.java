package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Page<StudentEntity> findBySemester(String semester, Pageable pageable);

    Page<StudentEntity> findByDegree(String degree, Pageable pageable);

    Page<StudentEntity> findBySemesterAndDegree(String semester, String degree, Pageable pageable);
}
