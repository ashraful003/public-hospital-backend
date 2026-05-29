package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.DurationRequest;
import com.myapp.public_hospital_backend.model.Duration;
import com.myapp.public_hospital_backend.repository.DurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DurationService {
    private final DurationRepository durationRepository;

    public DurationService(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    public Duration createDuration(DurationRequest request) {
        String nationalId = request.getNationalId().trim();
        String durationValue = request.getDuration().trim();
        boolean exists =
                !durationRepository
                        .findByNationalIdAndDuration(nationalId, durationValue)
                        .isEmpty();
        if (exists) {
            throw new RuntimeException("Duration already exists");
        }
        Duration duration = new Duration();
        duration.setNationalId(nationalId);
        duration.setDuration(durationValue);
        return durationRepository.save(duration);
    }

    public List<Duration> getByNationalId(String nationalId) {
        return durationRepository.findByNationalId(nationalId);
    }

    public Duration updateDuration(Long id, DurationRequest request) {
        Duration duration = durationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Duration not found")
                );
        duration.setNationalId(request.getNationalId().trim());
        duration.setDuration(request.getDuration().trim());
        return durationRepository.save(duration);
    }

    public void deleteDuration(Long id) {
        Duration duration = durationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Duration not found")
                );
        durationRepository.delete(duration);
    }
}