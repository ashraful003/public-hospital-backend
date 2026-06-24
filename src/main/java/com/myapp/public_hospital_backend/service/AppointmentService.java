package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.AppointmentRequest;
import com.myapp.public_hospital_backend.model.Appointment;
import com.myapp.public_hospital_backend.model.AppointmentStatus;
import com.myapp.public_hospital_backend.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AppointmentService {
    private static final String AUTO_CANCEL_REASON = "You didn't visit on time";
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> createAppointment(AppointmentRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        boolean exists = repository.existsByDoctorIdAndDoctorNameAndPatientIdAndDateAndStartTime(
                request.getDoctorId(),
                request.getDoctorName(),
                request.getPatientId(),
                request.getDate(),
                request.getStartTime()
        );
        if (exists) {
            response.put("message", "Appointment already exists");
            return response;
        }
        long count = repository.countByDoctorIdAndDoctorNameAndDate(
                request.getDoctorId(),
                request.getDoctorName(),
                request.getDate()
        );
        int nextNumber = (int) count + 101;
        String prefix = switch (request.getSpecialist().toLowerCase()) {
            case "cardiology" -> "C";
            case "medicine specialist" -> "M";
            case "dermatology" -> "D";
            default -> "G";
        };
        String serialNo = prefix + "-" + nextNumber;
        Appointment appointment = new Appointment();
        appointment.setSerialNo(serialNo);
        appointment.setDoctorId(request.getDoctorId());
        appointment.setDoctorName(request.getDoctorName());
        appointment.setSpecialist(request.getSpecialist());
        appointment.setPatientId(request.getPatientId());
        appointment.setPatientName(request.getPatientName());
        appointment.setDate(request.getDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setDay(request.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        appointment.setStatus(AppointmentStatus.WAITING);
        appointment.setReason(request.getReason());
        repository.save(appointment);
        response.put("message", "Appointment created successfully");
        return response;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = repository.findAll();
        autoCancelExpired(appointments);
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = repository.findByPatientId(patientId);
        autoCancelExpired(appointments);
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = repository.findByDoctorId(doctorId);
        autoCancelExpired(appointments);
        return appointments;
    }

    public Map<String, Object> updateAppointmentStatus(Long id, AppointmentStatus status, String reason) {
        Map<String, Object> response = new LinkedHashMap<>();
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        if ((status == AppointmentStatus.CANCELLED || status == AppointmentStatus.REJECTED)) {
            if (reason == null || reason.trim().isEmpty()) {
                response.put("message", "Reason is required for CANCELLED or REJECTED");
                return response;
            }
            appointment.setReason(reason);
        } else {
            appointment.setReason(reason != null ? reason : appointment.getReason());
        }
        repository.save(appointment);
        response.put("message", "Status updated successfully");
        return response;
    }

    public Map<String, Object> deleteAppointment(Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (!repository.existsById(id)) {
            response.put("message", "Appointment not found");
            return response;
        }
        repository.deleteById(id);
        response.put("message", "Appointment deleted successfully");
        return response;
    }

    private void autoCancelExpired(List<Appointment> appointments) {
        LocalDate today = LocalDate.now();
        for (Appointment appointment : appointments) {
            if (isExpiredAndWaiting(appointment, today)) {
                appointment.setStatus(AppointmentStatus.CANCELLED);
                appointment.setReason(AUTO_CANCEL_REASON);
                repository.save(appointment);
            }
        }
    }

    private boolean isExpiredAndWaiting(
            Appointment appointment,
            LocalDate today
    ) {
        if (appointment.getStatus() != AppointmentStatus.WAITING) {
            return false;
        }
        LocalDate appointmentDate = appointment.getDate();
        if (appointmentDate == null) {
            return false;
        }
        return appointmentDate.isBefore(today);
    }
}