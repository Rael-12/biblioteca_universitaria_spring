package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    List<UserResponse> findAll();

    List<UserResponse> findByFirstName(String firstName);

    List<UserResponse> findByLastName(String lastName);

    Optional<UserResponse> findByEmail(String email);

    Optional<UserResponse> findByPhoneNumber(String phoneNumber);

    void softDelete(Long id);

    void delete(Long id);

    Optional<UserResponse> updateUser(UserRequest userRequest, Long id);
}
