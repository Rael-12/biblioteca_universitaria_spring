package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {

    Optional<UserResponse> findById(Long id);

    PageResponse<UserResponse> findAll(Pageable pageable);

    PageResponse<UserResponse> findByFirstName(String firstName, Pageable  pageable);

    PageResponse<UserResponse> findByLastName(String lastName, Pageable pageable);

    Optional<UserResponse> findByEmail(String email);

    Optional<UserResponse> findByPhoneNumber(String phoneNumber);

    void softDelete(Long id);

    void delete(Long id);

    Optional<UserResponse> updateUser(UserRequest userRequest, Long id);

    void returnBook(Long id);

    void loanBook(Long id);
}
