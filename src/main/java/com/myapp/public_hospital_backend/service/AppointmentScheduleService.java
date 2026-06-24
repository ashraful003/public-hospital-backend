package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.AppointmentScheduleRequest;
import com.myapp.public_hospital_backend.model.AppointmentSchedule;
import com.myapp.public_hospital_backend.repository.AppointmentScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduleService {
    private final AppointmentScheduleRepository appointmentScheduleRepository;

    public AppointmentScheduleService(
            AppointmentScheduleRepository appointmentScheduleRepository) {
        this.appointmentScheduleRepository = appointmentScheduleRepository;
    }

    public AppointmentSchedule createAppointment(
            AppointmentScheduleRequest request) {
        boolean exists = appointmentScheduleRepository
                .existsByNationalIdAndDateAndStartTime(
                        request.getNationalId(),
                        request.getDate(),
                        request.getStartTime());
        if (exists) {
            throw new IllegalStateException("An appointment already exists");
        }
        AppointmentSchedule appointment = new AppointmentSchedule();

        appointment.setNationalId(request.getNationalId());
        appointment.setDay(request.getDay());
        appointment.setDate(request.getDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        return appointmentScheduleRepository.save(appointment);
    }

    public List<AppointmentSchedule> getAllAppointments() {
        return appointmentScheduleRepository.findAll();
    }

    public AppointmentSchedule getAppointmentById(
            Long id) {
        return appointmentScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Appointment not found with id: " + id));
    }

    public List<AppointmentSchedule> getAppointmentsByNationalId(String nationalId) {
        return appointmentScheduleRepository
                .findByNationalId(nationalId);
    }

    public AppointmentSchedule updateAppointment(Long id, AppointmentScheduleRequest request) {
        AppointmentSchedule existing = getAppointmentById(id);
        boolean duplicate = appointmentScheduleRepository
                .existsByNationalIdAndDateAndStartTime(
                        request.getNationalId(),
                        request.getDate(),
                        request.getStartTime());

        if (duplicate &&
                (!existing.getNationalId().equals(request.getNationalId())
                        || !existing.getDate().equals(request.getDate())
                        || !existing.getStartTime().equals(request.getStartTime()))) {
            throw new IllegalStateException("Another appointment already exists for this National ID at the selected date and time.");
        }
        existing.setNationalId(request.getNationalId());
        existing.setDay(request.getDay());
        existing.setDate(request.getDate());
        existing.setStartTime(request.getStartTime());
        existing.setEndTime(request.getEndTime());
        return appointmentScheduleRepository.save(existing);
    }

    public void deleteAppointment(Long id) {
        AppointmentSchedule existing = getAppointmentById(id);
        appointmentScheduleRepository.delete(existing);
    }
}