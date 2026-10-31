package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(UserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName).stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserResponse);
    }

    @Override
    public Optional<UserResponse> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(userMapper::userEntityToUserResponse);
    }

    @Override
    public void softDelete(Long id) {
        Integer rowsAffect = userRepository.softDelete(id);

        if (rowsAffect == 0) {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("user not found"));
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserResponse> updateUser(UserRequest userRequest, Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setBirthdate(userRequest.birthdate());
        user.setActive(userRequest.active());

        return Optional.of(userRepository.save(user))
                .map(userMapper::userEntityToUserResponse);
    }
}
