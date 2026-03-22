package com.example.mindarch.Config;

import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    private static final String DEFAULT_SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long DEFAULT_EXPIRATION = 86400000L;

    public String getSecret() {
        String secret = System.getenv("JWT_SECRET");
        return secret != null && !secret.isBlank() ? secret : DEFAULT_SECRET;
    }

    public long getExpiration() {
        String expiration = System.getenv("JWT_EXPIRATION");
        if (expiration != null && !expiration.isBlank()) {
            try {
                return Long.parseLong(expiration);
            } catch (NumberFormatException e) {
                return DEFAULT_EXPIRATION;
            }
        }
        return DEFAULT_EXPIRATION;
    }
}
