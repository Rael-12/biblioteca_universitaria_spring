package com.rauljaimes.bibliotecaUniversitariaSpring.user.repository;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String lastName);

    Optional<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
