package com.myapp.public_hospital_backend.repository;
import com.myapp.public_hospital_backend.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    boolean existsByParkingNo(String parkingNo);
}