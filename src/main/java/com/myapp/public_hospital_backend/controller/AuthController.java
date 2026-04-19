package com.myapp.public_hospital_backend.controller;

import com.myapp.public_hospital_backend.dto.*;
import com.myapp.public_hospital_backend.model.BloodProfile;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final OtpService otpService;
    private final PasswordService passwordService;
    private final UserService userService;
    private final BloodProfileService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email is required"));
        }
        if (!authService.isUserRegistered(email)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }
        String message = otpService.sendOtp(email);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        if (email == null || code == null || email.isEmpty() || code.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email and OTP code are required"));
        }
        try {
            otpService.verifyOtp(email, code);
            return ResponseEntity.ok(Map.of("message", "OTP verified successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String response = passwordService.changePassword(request);
            return ResponseEntity.ok(Map.of("message", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
        try {
            User user = userService.getCurrentUserByEmail(email);
            UserProfileResponse res = new UserProfileResponse();
            res.setNationalId(user.getNationalId());
            res.setName(user.getName());
            res.setEmail(user.getEmail());
            res.setDob(user.getDob());
            res.setPhone(user.getPhone());
            res.setAddress(user.getAddress());
            res.setDegree(user.getDegree());
            res.setInstitute(user.getInstitute());
            res.setLicense(user.getLicense());
            res.setSpecialist(user.getSpecialist());
            res.setWeight(user.getWeight());
            res.setRole(user.getRole().name());
            if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                res.setImageUrl(user.getImageUrl());
            } else if (user.getImageUrl() != null) {
                String fileName = System.currentTimeMillis() + ".jpg";
                byte[] imageBytes = java.util.Base64.getDecoder()
                        .decode(user.getImageUrl());
                Path uploadDir = Paths.get("uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                Files.write(uploadDir.resolve(fileName), imageBytes);
                String imageUrl = "/uploads/" + fileName;
                user.setImageUrl(imageUrl);
                userService.save(user);
                res.setImageUrl(imageUrl);
            } else {
                res.setImageUrl(null);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request) {
        try {
            User user = userService.getCurrentUserByEmail(request.getEmail());
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            user.setAddress(request.getAddress());
            user.setWeight(request.getWeight());
            user.setInstitute(request.getInstitute());
            user.setDegree(request.getDegree());
            user.setLicense(request.getLicense());
            user.setSpecialist(request.getSpecialist());
            if (request.getDob() != null && !request.getDob().isEmpty()) {
                user.setDob(java.time.LocalDate.parse(request.getDob()));
            }
            User savedUser = userService.save(user);
            UserProfileResponse res = new UserProfileResponse();
            res.setNationalId(savedUser.getNationalId());
            res.setName(savedUser.getName());
            res.setEmail(savedUser.getEmail());
            res.setPhone(savedUser.getPhone());
            res.setAddress(savedUser.getAddress());
            res.setDob(savedUser.getDob());
            res.setWeight(savedUser.getWeight());
            res.setInstitute(savedUser.getInstitute());
            res.setDegree(savedUser.getDegree());
            res.setLicense(savedUser.getLicense());
            res.setSpecialist(savedUser.getSpecialist());
            res.setRole(savedUser.getRole().name());
            return ResponseEntity.ok(Map.of(
                    "message", "Profile updated successfully",
                    "data", res
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/update-profile-image-json")
    public ResponseEntity<?> uploadImage(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String base64 = request.get("imageBase64");
            if (email == null || base64 == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Email & image required"));
            }
            User user = userService.getCurrentUserByEmail(email);
            if (user == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "User not found"));
            }
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }
            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64);
            String fileName = System.currentTimeMillis() + ".jpg";
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Files.write(uploadDir.resolve(fileName), imageBytes);
            userService.save(user);
            return ResponseEntity.ok(Map.of(
                    "message", "Upload success"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/change-old-password")
    public ResponseEntity<?> changeOldPassword(@RequestBody ChangeOldPasswordRequest request) {
        try {
            String response = passwordService.changeOldPassword(request);
            return ResponseEntity.ok(Map.of("message", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<BloodProfile> getByEmail(@PathVariable String email) {
        return service.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/blood")
    public ResponseEntity<BloodProfile> create(@RequestBody BloodProfile profile) {
        return ResponseEntity.ok(service.save(profile));
    }

    @PutMapping("/{email}")
    public ResponseEntity<BloodProfile> update(
            @PathVariable String email,
            @RequestBody BloodProfile profile) {
        return ResponseEntity.ok(service.update(email, profile));
    }
}