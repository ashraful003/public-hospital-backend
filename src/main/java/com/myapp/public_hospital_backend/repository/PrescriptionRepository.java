package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatientId(String patientId);
}