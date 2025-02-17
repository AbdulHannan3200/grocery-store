package com.alexa.store.service;

import com.alexa.store.entity.User;
import com.alexa.store.exception.UserAlreadyExistsException;
import com.alexa.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // Ensures proper transaction handling
    public User addUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()
        ) {
            throw new UserAlreadyExistsException("This user with email :"+user.getEmail()+" is already present!");
        }
        user.setId(0);

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email) { // Corrected
        return userRepository.findByEmail(email);
    }

}
