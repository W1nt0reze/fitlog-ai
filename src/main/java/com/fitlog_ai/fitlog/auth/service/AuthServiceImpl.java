package com.fitlog_ai.fitlog.auth.service;

import com.fitlog_ai.fitlog.auth.dto.RegisterRequest;
import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.common.exception.ResourceIsPresentException;
import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterRequest request) {
        String email = request.email();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResourceIsPresentException("Email already in use");
        }

        User newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setHeight(request.height());
        newUser.setBirthDate(request.birthDate());
        newUser.setGoal(request.goal());
        newUser.setActivityLevel(request.activityLevel());
        userRepository.save(newUser);
    }
}
