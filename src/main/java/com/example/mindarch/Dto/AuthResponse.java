package com.example.mindarch.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String email;
    private String token;
}

