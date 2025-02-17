package com.alexa.store.controller;

// ... imports

import com.alexa.store.entity.User;
import com.alexa.store.exception.UserAlreadyExistsException;
import com.alexa.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    // ... other code
    @Autowired
    private UserService userService;

    @PostMapping("/add_user")
    public ResponseEntity<?> createUser(@RequestBody User user) { // Corrected
        try {
            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) { // Corrected
        Optional<User> user = userService.getByEmail(email);
        return user.orElse(null); // Or throw an exception if user is not found
    }
}