package com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.professor.ProfessorResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.student.StudentResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.ProfessorEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.StudentEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    StudentResponse studentEntityToResponse(StudentEntity studentEntity);

    ProfessorResponse professorEntityToResponse(ProfessorEntity professorEntity);

    UserResponse userEntityToUserResponse(UserEntity userEntity);
}
