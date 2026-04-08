package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.model.otp.Otp;
import com.myapp.public_hospital_backend.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;

    private String generateOtp() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public String sendOtp(String email) {
        String otpCode = generateOtp();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusMinutes(5);
        Otp otp = otpRepository.findByEmail(email)
                .map(existingOtp -> {
                    existingOtp.setCode(otpCode);
                    existingOtp.setVerified(false);
                    existingOtp.setCreatedAt(now);
                    existingOtp.setExpiresAt(expiry);
                    return existingOtp;
                })
                .orElse(
                        Otp.builder()
                                .email(email)
                                .code(otpCode)
                                .verified(false)
                                .createdAt(now)
                                .expiresAt(expiry)
                                .build()
                );

        otpRepository.save(otp);
        System.out.println("OTP for " + email + " : " + otpCode);
        return "OTP sent successfully";
    }

    public boolean verifyOtp(String email, String code) {
        Otp otp = otpRepository.findByEmailAndCode(email, code)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));
        if (otp.isVerified()) {
            throw new RuntimeException("OTP already used");
        }
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }
        otp.setVerified(true);
        otpRepository.save(otp);
        return true;
    }

    public void validateOtpForPasswordReset(String email) {
        Otp otp = otpRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));
        if (!otp.isVerified()) {
            throw new RuntimeException("OTP not verified");
        }
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }
        otp.setVerified(false);
        otpRepository.save(otp);
    }
}