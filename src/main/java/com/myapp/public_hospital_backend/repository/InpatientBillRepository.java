package com.myapp.public_hospital_backend.repository;
import com.myapp.public_hospital_backend.model.InpatientBill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InpatientBillRepository extends JpaRepository<InpatientBill, Long> {
    Optional<InpatientBill> findByAdmissionId(Long admissionId);
    boolean existsByAdmissionId(Long admissionId);
    List<InpatientBill> findByPatientId(Long patientId);
}
