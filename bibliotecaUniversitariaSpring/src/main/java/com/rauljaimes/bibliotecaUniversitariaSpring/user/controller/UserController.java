package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/${api.version}/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/firstName/{firstName}")
    public List<UserResponse> findByFirstName(@PathVariable String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/lastname/{lastname}")
    public List<UserResponse> findByLastName(@PathVariable String lastName) {
        return userService.findByLastName(lastName);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {

        Optional<UserResponse> user = userService.findByEmail(email);

        return user.map(userResponse -> ResponseEntity.status(HttpStatus.OK)
                        .body(userResponse))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<UserResponse> findByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<UserResponse> user = userService.findByPhoneNumber(phoneNumber);

        return user.map(userResponse -> ResponseEntity.status(HttpStatus.OK)
                        .body(userResponse))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        userService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest,
                                                   @PathVariable Long id) {
        return ResponseEntity.of(userService.updateUser(userRequest, id));
    }

}
