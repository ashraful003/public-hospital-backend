package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineTypeRepository extends JpaRepository<MedicineType, Long> {
    List<MedicineType> findByNationalId(String nationalId);

    Optional<MedicineType> findByMedicineTypeAndNationalId(String medicineType, String nationalId);
}