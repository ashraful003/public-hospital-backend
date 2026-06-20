package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.HospitalSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<HospitalSeat, Long> {
    Optional<HospitalSeat> findBySeatNo(String seatNo);

    boolean existsBySeatNo(String seatNo);

    List<HospitalSeat> findByType(String type);

    List<HospitalSeat> findByStatus(Boolean status);

    List<HospitalSeat> findByTypeAndStatus(String type, Boolean status);

    @Query("SELECT COUNT(s) FROM HospitalSeat s WHERE s.type = :type AND s.status = true")
    Long countAvailableByType(@Param("type") String type);
}
