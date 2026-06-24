package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDoctorNameAndPatientIdAndDateAndStartTime(
            Long doctorId,
            String doctorName,
            Long patientId,
            LocalDate date,
            LocalTime startTime
    );

    long countByDoctorIdAndDoctorNameAndDate(
            Long doctorId,
            String doctorName,
            LocalDate date
    );

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);
}