package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findBySemester(String semester);

    List<StudentEntity> findByDegree(String degree);

    Optional<StudentEntity> findBySemesterAndDegree(String semester, String degree);
}
