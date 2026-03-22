package com.example.mindarch.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


// DTO for login request
@Data
@Builder
@AllArgsConstructor

public class SigninRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;


}

