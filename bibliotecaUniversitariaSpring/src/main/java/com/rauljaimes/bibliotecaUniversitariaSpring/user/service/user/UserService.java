package com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.entity.UserEntity;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.exceptions.UserNotFoundException;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.mapper.IUserMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.repository.UserRepository;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageMapper;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IUserMapper userMapper;
    private final PageMapper pageMapper;

    public UserService(UserRepository userRepository, IUserMapper userMapper, PageMapper pageMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.pageMapper = pageMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userEntityToUserResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<UserResponse> findAll(Pageable pageable) {
        Page<UserResponse> users = userRepository.findAll(pageable)
                .map(userMapper::userEntityToUserResponse);

        return pageMapper.toPageResponse(users);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<UserResponse> findByFirstName(String firstName, Pageable pageable) {
        Page<UserResponse> users = userRepository.findByFirstName(firstName, pageable)
                .map(userMapper::userEntityToUserResponse);

        return pageMapper.toPageResponse(users);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<UserResponse> findByLastName(String lastName, Pageable pageable) {
        Page<UserResponse> users = userRepository.findByLastName(lastName, pageable)
                .map(userMapper::userEntityToUserResponse);

        return pageMapper.toPageResponse(users);
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
