package com.myapp.public_hospital_backend.service;
import com.myapp.public_hospital_backend.dto.PrescriptionRequest;
import com.myapp.public_hospital_backend.model.Prescription;
import com.myapp.public_hospital_backend.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PrescriptionService {
    private final PrescriptionRepository repository;
    private final BillService billService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public PrescriptionService(PrescriptionRepository repository, BillService billService) {
        this.repository = repository;
        this.billService = billService;
    }
    public Prescription createPrescription(
            PrescriptionRequest req
    ) {
        try {
            Prescription p = new Prescription();
            p.setDoctorName(req.getDoctorName());
            p.setDoctorBnName(req.getDoctorBnName());
            p.setDoctorDegree(req.getDoctorDegree());
            p.setDoctorBnDegree(req.getDoctorBnDegree());
            p.setDoctorSpecialist(req.getDoctorSpecialist());
            p.setDoctorBnSpecialist(req.getDoctorBnSpecialist());
            p.setDoctorInstitute(req.getDoctorInstitute());
            p.setDoctorBnInstitute(req.getDoctorBnInstitute());
            p.setDoctorLicense(req.getDoctorLicense());
            p.setDoctorBnLicense(req.getDoctorBnLicense());
            p.setDoctorBnVisitingTime(req.getDoctorBnVisitingTime());
            p.setPatientId(req.getPatientId());
            p.setPatientName(req.getPatientName());
            p.setPatientAge(req.getPatientAge());
            p.setPatientWeight(req.getPatientWeight());
            p.setProblems(req.getProblems());
            p.setBloodPressure(req.getBloodPressure());
            p.setPulse(req.getPulse());
            p.setTemperature(req.getTemperature());
            p.setMedicines(objectMapper.writeValueAsString(req.getMedicines()));
            p.setAdvice(req.getAdvice());
            p.setTests(objectMapper.writeValueAsString(req.getTests()));
            p.setNextMeet(req.getNextMeet());
            p.setDate(req.getDate());
            Prescription saved = repository.save(p);
            if (req.getTests() != null &&
                    !req.getTests().isEmpty()) {
                billService.createBillFromPrescription(saved, req.getTests());
            }
            return saved;
        } catch (Exception e) {
            throw new RuntimeException("Prescription save failed: " + e.getMessage());
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
            List<String> oldTests = parseTests(p.getTests());

            p.setDoctorName(req.getDoctorName());
            p.setDoctorBnName(req.getDoctorBnName());
            p.setDoctorDegree(req.getDoctorDegree());
            p.setDoctorBnDegree(req.getDoctorBnDegree());
            p.setDoctorSpecialist(req.getDoctorSpecialist());
            p.setDoctorBnSpecialist(req.getDoctorBnSpecialist());
            p.setDoctorInstitute(req.getDoctorInstitute());
            p.setDoctorBnInstitute(req.getDoctorBnInstitute());
            p.setDoctorLicense(req.getDoctorLicense());
            p.setDoctorBnLicense(req.getDoctorBnLicense());
            p.setDoctorBnVisitingTime(req.getDoctorBnVisitingTime());
            p.setPatientId(req.getPatientId());
            p.setPatientName(req.getPatientName());
            p.setPatientAge(req.getPatientAge());
            p.setPatientWeight(req.getPatientWeight());
            p.setProblems(req.getProblems());
            p.setBloodPressure(req.getBloodPressure());
            p.setPulse(req.getPulse());
            p.setTemperature(req.getTemperature());
            p.setMedicines(objectMapper.writeValueAsString(req.getMedicines()));
            p.setAdvice(req.getAdvice());
            p.setTests(objectMapper.writeValueAsString(req.getTests()));
            p.setNextMeet(req.getNextMeet());
            p.setDate(req.getDate());

            Prescription saved = repository.save(p);

            List<String> newTests = findNewTests(oldTests, req.getTests());
            if (!newTests.isEmpty()) {
                billService.createBillFromPrescription(saved, newTests);
            }

            return saved;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error updating prescription: " + e.getMessage()
            );
        }
    }

    private List<String> parseTests(String testsJson) {
        if (testsJson == null || testsJson.isBlank()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(testsJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<String> findNewTests(List<String> oldTests, List<String> incomingTests) {
        List<String> newTests = new ArrayList<>();
        if (incomingTests == null || incomingTests.isEmpty()) {
            return newTests;
        }
        for (String test : incomingTests) {
            if (test != null && !oldTests.contains(test)) {
                newTests.add(test);
            }
        }
        return newTests;
    }
}