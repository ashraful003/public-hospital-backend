package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByPatientId(String patientId);
}