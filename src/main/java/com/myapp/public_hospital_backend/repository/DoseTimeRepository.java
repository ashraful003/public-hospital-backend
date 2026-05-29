package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.DoseTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoseTimeRepository extends JpaRepository<DoseTime, Long> {
    List<DoseTime> findByNationalId(String nationalId);

    Optional<DoseTime> findByDoseTimeAndNationalId(String doseTime, String nationalId);
}