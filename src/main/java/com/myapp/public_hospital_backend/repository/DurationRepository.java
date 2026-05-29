package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DurationRepository extends JpaRepository<Duration, Long> {
    List<Duration> findByNationalId(String nationalId);

    List<Duration> findByNationalIdAndDuration(
            String nationalId,
            String duration
    );
}
