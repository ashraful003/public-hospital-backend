package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByPatientId(String patientId);
    List<Report> findByCenterName(String centerName);
}