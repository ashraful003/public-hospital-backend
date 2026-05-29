package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.PrescriptionRequest;
import com.myapp.public_hospital_backend.model.Prescription;
import com.myapp.public_hospital_backend.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    private final PrescriptionRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public Prescription createPrescription(PrescriptionRequest req) {
        try {
            Prescription p = new Prescription();
            p.setDoctorName(req.doctorName);
            p.setDoctorDegree(req.doctorDegree);
            p.setDoctorSpecialist(req.doctorSpecialist);
            p.setDoctorInstitute(req.doctorInstitute);
            p.setDoctorLicense(req.doctorLicense);
            p.setPatientId(req.patientId);
            p.setPatientName(req.patientName);
            p.setPatientAge(req.patientAge);
            p.setPatientWeight(req.patientWeight);
            p.setProblems(req.problems);
            p.setBloodPressure(req.bloodPressure);
            p.setPulse(req.pulse);
            p.setTemperature(req.temperature);
            p.setMedicines(objectMapper.writeValueAsString(req.medicines));
            p.setAdvice(req.advice);
            p.setTests(objectMapper.writeValueAsString(req.tests));
            p.setNextMeet(req.nextMeet);
            p.setDate(req.date);
            return repository.save(p);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error creating prescription: " + e.getMessage()
            );
        }
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return repository.findById(id);
    }

    public List<Prescription> getPrescriptionsByPatientId(String patientId) {
        return repository.findByPatientId(patientId);
    }

    public Prescription updatePrescription(
            Long id,
            PrescriptionRequest req
    ) {
        Prescription p = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription not found"));
        try {
            p.setDoctorName(req.doctorName);
            p.setDoctorDegree(req.doctorDegree);
            p.setDoctorSpecialist(req.doctorSpecialist);
            p.setDoctorInstitute(req.doctorInstitute);
            p.setDoctorLicense(req.doctorLicense);
            p.setPatientId(req.patientId);
            p.setPatientName(req.patientName);
            p.setPatientAge(req.patientAge);
            p.setPatientWeight(req.patientWeight);
            p.setProblems(req.problems);
            p.setBloodPressure(req.bloodPressure);
            p.setPulse(req.pulse);
            p.setTemperature(req.temperature);
            p.setMedicines(objectMapper.writeValueAsString(req.medicines));
            p.setAdvice(req.advice);
            p.setTests(objectMapper.writeValueAsString(req.tests));
            p.setNextMeet(req.nextMeet);
            p.setDate(req.date);
            return repository.save(p);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error updating prescription: " + e.getMessage()
            );
        }
    }
}