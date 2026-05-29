package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.DoseTimeRequest;
import com.myapp.public_hospital_backend.model.DoseTime;
import com.myapp.public_hospital_backend.repository.DoseTimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoseTimeService {
    private final DoseTimeRepository repository;

    public DoseTimeService(DoseTimeRepository repository) {
        this.repository = repository;
    }

    public String createDoseTime(DoseTimeRequest request) {
        Optional<DoseTime> existing =
                repository.findByDoseTimeAndNationalId(request.getDoseTime(), request.getNationalId());
        if (existing.isPresent()) {
            return "Dose time already exists for this user";
        }
        DoseTime doseTime = new DoseTime();
        doseTime.setDoseTime(request.getDoseTime());
        doseTime.setNationalId(request.getNationalId());
        repository.save(doseTime);
        return "Dose time created successfully";
    }

    public List<DoseTime> getDoseTimeByNationalId(String nationalId) {
        return repository.findByNationalId(nationalId);
    }

    public String updateDoseTime(Long id, DoseTimeRequest request) {
        Optional<DoseTime> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "Dose time not found";
        }
        DoseTime doseTime = optional.get();
        Optional<DoseTime> duplicate =
                repository.findByDoseTimeAndNationalId(request.getDoseTime(), request.getNationalId());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            return "Dose time already exists for this user";
        }
        doseTime.setDoseTime(request.getDoseTime());
        doseTime.setNationalId(request.getNationalId());
        repository.save(doseTime);
        return "Dose time updated successfully";
    }

    public String deleteDoseTime(Long id) {
        if (!repository.existsById(id)) {
            return "Dose time not found";
        }
        repository.deleteById(id);
        return "Dose time deleted successfully";
    }
}