package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.ChangeOldPasswordRequest;
import com.myapp.public_hospital_backend.dto.ChangePasswordRequest;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final UserRepository userRepository;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;

    public String changePassword(ChangePasswordRequest request) {
        otpService.validateOtpForPasswordReset(request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "Password changed successfully";
    }

    public String changeOldPassword(ChangeOldPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return "Password updated successfully";
    }
}
