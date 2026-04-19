package com.myapp.public_hospital_backend.dto;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String message;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String accessToken, String refreshToken, String message, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}