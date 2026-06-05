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
            p.setDoctorDegree(req.getDoctorDegree());
            p.setDoctorSpecialist(req.getDoctorSpecialist());
            p.setDoctorInstitute(req.getDoctorInstitute());
            p.setDoctorLicense(req.getDoctorLicense());
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
                billService.createBillFromPrescription(saved,req.getTests());
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