package com.alexa.store.controller;

// ... imports

import com.alexa.store.dto.UserDto;
import com.alexa.store.entity.User;
import com.alexa.store.exception.UserAlreadyExistsException;
import com.alexa.store.exception.UserNotFoundException;
import com.alexa.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    // ... other code
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no users
            }
            return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK with the list of users
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace(); // Or use a proper logger
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    @PostMapping("/add_user")
    public ResponseEntity<?> createUser(@RequestBody User user) { // Corrected
        try {
            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable String email) { // Corrected
        Optional<User> user = userService.getByEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException("User is not found in the database!")); // Or throw an exception if user is not found
    }
}