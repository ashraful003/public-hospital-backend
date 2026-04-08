package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.otp.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmail(String email);

    Optional<Otp> findByEmailAndCode(String email, String code);
}