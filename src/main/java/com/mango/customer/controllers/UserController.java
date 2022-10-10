package com.mango.customer.controllers;

import com.mango.customer.dtos.UserDto;
import com.mango.customer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/users")
    ResponseEntity<?> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<?> getUser(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

	@PostMapping("/signin")
	ResponseEntity<?> loginUser(@RequestBody @Valid final UserDto userDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.signing(userDto));
	}

    @PostMapping("/users/create")
    ResponseEntity<?> createUser(@RequestBody @Valid final UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(userDto));
    }

    @PutMapping("/updateUser")
    ResponseEntity<?> updateUser(@RequestBody @Valid final UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.updateUser(userDto));
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable(name ="id") @NotNull Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("User %s Deleted", id));
    }
}
