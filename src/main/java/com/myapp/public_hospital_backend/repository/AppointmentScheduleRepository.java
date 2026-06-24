package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.AppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Long> {
    List<AppointmentSchedule> findByNationalId(String nationalId);

    boolean existsByNationalIdAndDateAndStartTime(String nationalId, LocalDate date, java.time.LocalTime startTime);
}