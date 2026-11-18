package com.rauljaimes.bibliotecaUniversitariaSpring.user.controller;

import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserRequest;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.dto.user.UserResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.user.service.user.IUserService;
import com.rauljaimes.bibliotecaUniversitariaSpring.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/${api.version}/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<UserResponse>> findAll(@PageableDefault(size = 20, sort = "firstName",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<PageResponse<UserResponse>> findByFirstName(@PathVariable String firstName,
                                                                      @PageableDefault(size = 20, sort = "firstName",
                                                                              direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(userService.findByFirstName(firstName, pageable));
    }

    @GetMapping("/lastname/{lastname}")
        public ResponseEntity<PageResponse<UserResponse>> findByLastName(@PathVariable String lastName,
                                                                         @PageableDefault(size = 20, sort = "firstName",
                                                                                 direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(userService.findByLastName(lastName, pageable));
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