package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoseRepository extends JpaRepository<Dose, Long> {
    List<Dose> findByNationalId(String nationalId);

    Optional<Dose> findByNationalIdAndDose(String nationalId, String dose);

    boolean existsByNationalIdAndDose(String nationalId, String dose);
}
