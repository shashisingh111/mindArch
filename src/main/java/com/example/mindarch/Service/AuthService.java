package com.example.mindarch.Service;

import com.example.mindarch.Dto.AuthResponse;
import com.example.mindarch.Dto.SignUpResponse;
import com.example.mindarch.Dto.SigninRequest;
import com.example.mindarch.Dto.SignupRequest;
import com.example.mindarch.Entity.User;
import com.example.mindarch.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


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

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("Login successful", user.getEmail(), token);
    }
}

