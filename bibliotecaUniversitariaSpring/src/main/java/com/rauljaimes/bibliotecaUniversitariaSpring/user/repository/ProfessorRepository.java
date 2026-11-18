package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long>{
    Page<ProfessorEntity> findByDepartment(String department, Pageable pageable);
}
