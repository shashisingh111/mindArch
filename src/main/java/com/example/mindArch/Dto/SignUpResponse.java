package com.example.mindArch.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpResponse<T> {
    private String message;
    private T data;
}
