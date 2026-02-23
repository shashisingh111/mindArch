package com.example.mindArch.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.validation.constraints.Email;


@Data
public class SignupRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String name;
}


