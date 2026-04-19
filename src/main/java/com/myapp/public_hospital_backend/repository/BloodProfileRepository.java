package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.BloodProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodProfileRepository extends JpaRepository<BloodProfile, Long> {
    Optional<BloodProfile> findByEmail(String email);
}
