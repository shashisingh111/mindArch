package com.example.mindArch.Controller;

import com.example.mindArch.Dto.AuthResponse;
import com.example.mindArch.Dto.SignUpResponse;
import com.example.mindArch.Dto.SigninRequest;
import com.example.mindArch.Dto.SignupRequest;
import com.example.mindArch.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse<Void>> signup(@RequestBody SignupRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SigninRequest request) {

        AuthResponse response = authService.signIn(request);

        return ResponseEntity.ok(response);
    }
}
