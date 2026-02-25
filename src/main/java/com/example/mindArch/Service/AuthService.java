package com.example.mindArch.Service;

import com.example.mindArch.Dto.AuthResponse;
import com.example.mindArch.Dto.SignUpResponse;
import com.example.mindArch.Dto.SigninRequest;
import com.example.mindArch.Dto.SignupRequest;
import com.example.mindArch.Entity.User;
import com.example.mindArch.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public ResponseEntity<SignUpResponse<Void>> signUp(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new SignUpResponse<>("User already exists", null));
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setName(request.getName());

        userRepository.save(user);
        return ResponseEntity.ok(
                new SignUpResponse<>("User registered successfully", null)
        );
    }

    public AuthResponse signIn(SigninRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResponse("Login successful", user.getEmail());
    }
}

