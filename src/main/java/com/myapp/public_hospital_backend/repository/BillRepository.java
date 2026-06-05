package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByPatientId(String patientId);
}
