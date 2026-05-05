package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByNationalIdAndDate(String nationalId, LocalDate date);

    List<Attendance> findAllByOrderByDateDesc();

    List<Attendance> findByNationalIdOrderByDateDesc(String nationalId);
}
