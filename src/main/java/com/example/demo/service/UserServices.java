package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Registration;
import com.example.demo.entity.User;
import com.example.demo.loginCredential.UserLogin;
import com.example.demo.repo.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(Registration dto) {
        User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return "User data saved successfully";
    }

    public boolean verifyLogin(UserLogin loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getUserEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(loginDto.getUserPassword(), user.getPassword());
        }
        return false;
    }
}
