package com.goldenladder.backend.config.jwt;


import com.goldenladder.backend.model.Role;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private final Role role;

    public JwtResponse(String accessToken, String username, String email, Role role) {
        this.token = accessToken;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }
}