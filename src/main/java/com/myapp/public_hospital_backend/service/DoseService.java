package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.DoseRequest;
import com.myapp.public_hospital_backend.model.Dose;
import com.myapp.public_hospital_backend.repository.DoseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoseService {
    private final DoseRepository doseRepository;

    public DoseService(DoseRepository doseRepository) {
        this.doseRepository = doseRepository;
    }

    public String addDose(DoseRequest request) {
        boolean exists = doseRepository
                .existsByNationalIdAndDose(request.getNationalId(), request.getDose());
        if (exists) {
            return "Dose already exists";
        }
        Dose dose = new Dose();
        dose.setDose(request.getDose());
        dose.setNationalId(request.getNationalId());
        doseRepository.save(dose);
        return "Dose added successfully";
    }

    public List<Dose> getDoseByNationalId(String nationalId) {
        return doseRepository.findByNationalId(nationalId);
    }

    public String updateDose(Long id, DoseRequest request) {
        Optional<Dose> optional = doseRepository.findById(id);
        if (optional.isEmpty()) {
            return "Dose not found";
        }
        Optional<Dose> duplicate = doseRepository
                .findByNationalIdAndDose(request.getNationalId(), request.getDose());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            return "Dose already exists";
        }
        Dose dose = optional.get();
        dose.setDose(request.getDose());
        dose.setNationalId(request.getNationalId());
        doseRepository.save(dose);
        return "Dose updated successfully";
    }

    public String deleteDose(Long id) {
        if (!doseRepository.existsById(id)) {
            return "Dose not found";
        }
        doseRepository.deleteById(id);
        return "Dose deleted successfully";
    }
}