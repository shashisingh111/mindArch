package com.example.mindarch.Controller;

import com.example.mindarch.Dto.AuthResponse;
import com.example.mindarch.Dto.SignUpResponse;
import com.example.mindarch.Dto.SigninRequest;
import com.example.mindarch.Dto.SignupRequest;
import com.example.mindarch.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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
