package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.model.BloodProfile;
import com.myapp.public_hospital_backend.repository.BloodProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BloodProfileService {

    private final BloodProfileRepository repository;

    public BloodProfile save(BloodProfile profile) {
        return repository.save(profile);
    }

    public Optional<BloodProfile> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public BloodProfile update(String email, BloodProfile updated) {
        BloodProfile existing = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        existing.setLastDonateDate(updated.getLastDonateDate());

        return repository.save(existing);
    }
}
