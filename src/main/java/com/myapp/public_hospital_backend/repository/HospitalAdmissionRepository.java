package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.HospitalAdmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalAdmissionRepository extends JpaRepository<HospitalAdmission, Long> {

    List<HospitalAdmission> findByPatientId(Long patientId);

    boolean existsByPatientIdAndStatus(Long patientId, String status);

}