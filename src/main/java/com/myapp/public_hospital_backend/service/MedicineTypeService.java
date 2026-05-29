package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.MedicineTypeRequest;
import com.myapp.public_hospital_backend.model.MedicineType;
import com.myapp.public_hospital_backend.repository.MedicineTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineTypeService {
    private final MedicineTypeRepository repository;

    public MedicineTypeService(MedicineTypeRepository repository) {
        this.repository = repository;
    }

    public String createMedicineType(MedicineTypeRequest request) {
        boolean exists = repository
                .findByMedicineTypeAndNationalId(
                        request.getMedicineType(),
                        request.getNationalId()
                )
                .isPresent();
        if (exists) {
            return "Medicine type already exists";
        }
        MedicineType type = new MedicineType();
        type.setMedicineType(request.getMedicineType());
        type.setNationalId(request.getNationalId());
        repository.save(type);
        return "Medicine type created successfully";
    }

    public List<MedicineType> getMedicineType(String nationalId) {
        return repository.findByNationalId(nationalId);
    }

    public String updateMedicineType(Long id, MedicineTypeRequest request) {
        Optional<MedicineType> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "Not found";
        }
        boolean exists = repository
                .findByMedicineTypeAndNationalId(
                        request.getMedicineType(),
                        request.getNationalId()
                )
                .filter(t -> !t.getId().equals(id))
                .isPresent();
        if (exists) {
            return "This medicine type already exist";
        }
        MedicineType type = optional.get();
        type.setMedicineType(request.getMedicineType());
        type.setNationalId(request.getNationalId());
        repository.save(type);
        return "Updated";
    }

    public String deleteMedicineType(Long id) {
        if (!repository.existsById(id)) {
            return "Not found";
        }
        repository.deleteById(id);
        return "Deleted";
    }
}