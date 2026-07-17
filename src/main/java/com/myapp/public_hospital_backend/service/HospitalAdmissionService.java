package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.HospitalAdmissionRequest;
import com.myapp.public_hospital_backend.model.HospitalAdmission;
import com.myapp.public_hospital_backend.model.HospitalSeat;
import com.myapp.public_hospital_backend.repository.HospitalAdmissionRepository;
import com.myapp.public_hospital_backend.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HospitalAdmissionService {
    private final HospitalAdmissionRepository hospitalAdmissionRepository;
    private final SeatRepository seatRepository;
    private final InpatientBillService inpatientBillService;
    public HospitalAdmissionService(HospitalAdmissionRepository hospitalAdmissionRepository, SeatRepository seatRepository, InpatientBillService inpatientBillService) {
        this.hospitalAdmissionRepository = hospitalAdmissionRepository;
        this.seatRepository = seatRepository;
        this.inpatientBillService = inpatientBillService;
    }

    public String admitPatient(HospitalAdmissionRequest request) {

        boolean admitted = hospitalAdmissionRepository.existsByPatientIdAndStatus(request.getPatientId(), "ADMITTED");
        if (admitted) {
            return "This patient is already admitted";
        }
        HospitalSeat seat = seatRepository.findBySeatNo(request.getSeatNo())
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        if (!seat.getStatus()) {
            return "Seat is already occupied";
        }
        HospitalAdmission admission = new HospitalAdmission();
        admission.setPatientId(request.getPatientId());
        admission.setPatientName(request.getPatientName());
        admission.setPatientAge(request.getPatientAge());
        admission.setPatientWeight(request.getPatientWeight());
        admission.setPatientAddress(request.getPatientAddress());
        admission.setDoctorId(request.getDoctorId());
        admission.setDoctorName(request.getDoctorName());
        admission.setSeatNo(request.getSeatNo());
        admission.setAdmissionType(request.getAdmissionType());
        admission.setDiagnosis(request.getDiagnosis());
        admission.setRemarks(request.getRemarks());
        admission.setAdmissionDate(
                request.getAdmissionDate() != null
                        ? request.getAdmissionDate()
                        : LocalDateTime.now());
        admission.setExpectedDischargeDate(
                request.getExpectedDischargeDate());
        admission.setStatus("ADMITTED");
        admission.setAdmitedById(request.getAdmitedById());
        admission.setAdmitedByName(request.getAdmitedByName());
        hospitalAdmissionRepository.save(admission);
        seat.setStatus(false);
        seatRepository.save(seat);
        inpatientBillService.generateBillForAdmission(admission, seat);
        return "Patient admitted successfully";
    }

    public List<HospitalAdmission> getAllAdmissions() {
        return hospitalAdmissionRepository.findAll();
    }

    public HospitalAdmission getById(Long id) {
        return hospitalAdmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    public List<HospitalAdmission> getByPatientId(Long patientId) {
        return hospitalAdmissionRepository.findByPatientId(patientId);
    }

    public HospitalAdmission updateAdmission(Long id, HospitalAdmissionRequest request) {
        HospitalAdmission admission = getById(id);
        admission.setPatientName(request.getPatientName());
        admission.setPatientAge(request.getPatientAge());
        admission.setPatientWeight(request.getPatientWeight());
        admission.setPatientAddress(request.getPatientAddress());
        admission.setDoctorId(request.getDoctorId());
        admission.setDoctorName(request.getDoctorName());
        admission.setDiagnosis(request.getDiagnosis());
        admission.setRemarks(request.getRemarks());
        admission.setExpectedDischargeDate(request.getExpectedDischargeDate());
        return hospitalAdmissionRepository.save(admission);
    }

    public HospitalAdmission transferSeat(Long id, HospitalAdmissionRequest request) {
        HospitalAdmission admission = getById(id);
        if (!"ADMITTED".equalsIgnoreCase(admission.getStatus())) {
            throw new RuntimeException("Only admitted patients can be transferred.");
        }
        if (request.getNewSeatNo() == null ||
                request.getNewSeatNo().trim().isEmpty()) {
            throw new RuntimeException("New seat is required.");
        }
        if (admission.getSeatNo().equals(request.getNewSeatNo())) {
            throw new RuntimeException("Patient is already in this seat.");
        }
        HospitalSeat oldSeat = seatRepository.findBySeatNo(admission.getSeatNo()).orElseThrow(() -> new RuntimeException("Current seat not found"));
        HospitalSeat newSeat = seatRepository.findBySeatNo(request.getNewSeatNo()).orElseThrow(() -> new RuntimeException("New seat not found"));
        if (!newSeat.getStatus()) {
            throw new RuntimeException("Selected seat is already occupied.");
        }
        oldSeat.setStatus(true);
        seatRepository.save(oldSeat);
        newSeat.setStatus(false);
        seatRepository.save(newSeat);
        admission.setSeatNo(request.getNewSeatNo());
        if (request.getAdmissionType() != null && !request.getAdmissionType().trim().isEmpty()) {
            admission.setAdmissionType(request.getAdmissionType().trim());
        }
        if (request.getRemarks() != null && !request.getRemarks().trim().isEmpty()) {
            admission.setRemarks(request.getRemarks());
        }
        return hospitalAdmissionRepository.save(admission);
    }

    public String dischargePatient(Long id, HospitalAdmissionRequest request) {
        HospitalAdmission admission = getById(id);
        if (!"ADMITTED".equals(admission.getStatus())) {
            return "Patient is not currently admitted";
        }
        admission.setStatus("DISCHARGED");
        admission.setDischargeDate(LocalDateTime.now());
        admission.setDischargedById(request.getDischargedById());
        admission.setDischargedByName(request.getDischargedByName());
        admission.setRemarks(request.getRemarks());
        hospitalAdmissionRepository.save(admission);
        HospitalSeat seat = seatRepository.findBySeatNo(admission.getSeatNo()).orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setStatus(true);
        seatRepository.save(seat);
        return "Patient discharged successfully";
    }
}