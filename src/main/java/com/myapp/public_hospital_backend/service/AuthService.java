package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.*;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.model.UserRole;
import com.myapp.public_hospital_backend.repository.UserRepository;
import com.myapp.public_hospital_backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repo;
    private final JwtUtils jwt;
    private final PasswordEncoder encoder;

    public boolean isUserRegistered(String email) {
        return repo.existsByEmail(email);
    }

    public AuthResponse register(RegisterRequest req) {
        if (req.getEmail() == null || req.getPassword() == null) {
            throw new RuntimeException("Email and password required");
        }
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (repo.findByNationalId(req.getNationalId()).isPresent()) {
            throw new RuntimeException("National ID already exists");
        }
        User user = new User();
        user.setNationalId(req.getNationalId());
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        if (req.getDob() != null && !req.getDob().isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(req.getDob());
                user.setDob(dob);
            } catch (Exception e) {
                throw new RuntimeException("Invalid DOB format. Use yyyy-MM-dd");
            }
        }
        user.setPassword(encoder.encode(req.getPassword()));
        user.setWeight(req.getWeight());
        user.setImageUrl(req.getImageUrl());
        user.setInstitute(req.getInstitute());
        user.setDegree(req.getDegree());
        user.setLicense(req.getLicense());
        user.setSpecialist(req.getSpecialist());
        user.setIsActive(req.getIsActive() != null ? req.getIsActive() : true);
        try {
            user.setRole(
                    req.getRole() != null
                            ? UserRole.valueOf(req.getRole().toUpperCase())
                            : UserRole.PATIENT
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid role");
        }
        repo.save(user);
        return new AuthResponse(
                jwt.generateAccessToken(user.getEmail()),
                jwt.generateRefreshToken(user.getEmail()),
                "User Registered Successfully"
        );
    }

    public AuthResponse login(LoginRequest req) {
        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponse(
                jwt.generateAccessToken(user.getEmail()),
                jwt.generateRefreshToken(user.getEmail()),
                "Login Successful"
        );
    }

    public AuthResponse refreshToken(RefreshTokenRequest req) {
        String email = jwt.extractEmail(req.getRefreshToken());
        repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new AuthResponse(
                jwt.generateAccessToken(email),
                jwt.generateRefreshToken(email),
                "Token refreshed"
        );
    }
}