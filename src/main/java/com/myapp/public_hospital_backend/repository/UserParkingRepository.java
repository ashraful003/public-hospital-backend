package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.UserParking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserParkingRepository extends JpaRepository<UserParking, Long> {
    List<UserParking> findByPatientId(String patientId);

    List<UserParking> findByExitTimeIsNull();

    Optional<UserParking> findByVehicleNoAndExitTimeIsNull(String vehicleNo);

    boolean existsByVehicleNoAndExitTimeIsNull(String vehicleNo);
}