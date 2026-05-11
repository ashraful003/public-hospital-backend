package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByName(String name);

    boolean existsByMedicineNameAndPowerAndName(
            String medicineName,
            String power,
            String name
    );
}