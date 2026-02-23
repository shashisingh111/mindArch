package com.example.mindArch.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // ✅ THIS FIXES YOUR ERROR
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/signup", "/auth/signin").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}

