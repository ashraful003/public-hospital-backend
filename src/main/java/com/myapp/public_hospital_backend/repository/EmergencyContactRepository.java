package com.myapp.public_hospital_backend.repository;
import com.myapp.public_hospital_backend.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    List<EmergencyContact> findByEmergencyNumber(String emergencyNumber);
    List<EmergencyContact> findByEmergencyDoctorNumber(String emergencyDoctorNumber);
    List<EmergencyContact> findByEmergencyDoctorWhatsappNumber(String emergencyDoctorWhatsappNumber);
}