package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(UserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userEntityToUserResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName).stream()
                .map(userMapper::userEntityToUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponse> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(userMapper::userEntityToUserResponse);
    }

    @Transactional
    @Override
    public void softDelete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.softDelete();
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<UserResponse> updateUser(UserRequest userRequest, Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.updateUser(userRequest.firstName(), userRequest.lastName(), userRequest.email(),
                userRequest.phoneNumber(), userRequest.birthdate());

        return Optional.of(userRepository.save(user))
                .map(userMapper::userEntityToUserResponse);
    }

    @Transactional
    @Override
    public void returnBook(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.returnBook();
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void loanBook(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.loanBook();
        userRepository.save(user);
    }
}
