package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.model.Medicine;
import com.myapp.public_hospital_backend.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(
            MedicineRepository medicineRepository
    ) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine addMedicine(Medicine medicine) {
        String pharmaName = medicine.getName().trim();
        String medName = medicine.getMedicineName().trim();
        String power = medicine.getPower().trim();
        boolean exists =
                medicineRepository.existsByMedicineNameAndPowerAndName(
                        medName,
                        power,
                        pharmaName
                );
        if (exists) {
            throw new RuntimeException(
                    "Medicine already exists"
            );
        }
        medicine.setName(pharmaName);
        medicine.setMedicineName(medName);
        medicine.setPower(power);
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getMyMedicines(
            String name
    ) {
        return medicineRepository.findByName(name);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicine(Long id) {
        return medicineRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicine not found"
                        )
                );
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    public Medicine updateMedicine(
            Long id,
            Medicine updated
    ) {
        Medicine medicine = getMedicine(id);
        medicine.setMedicineName(
                updated.getMedicineName()
        );
        medicine.setPower(
                updated.getPower()
        );
        medicine.setName(
                updated.getName()
        );
        medicine.setUnitPrice(
                updated.getUnitPrice()
        );
        medicine.setTotalPrice(
                updated.getTotalPrice()
        );
        medicine.setIndications(
                updated.getIndications()
        );
        medicine.setPharmacology(
                updated.getPharmacology()
        );
        medicine.setDosage(
                updated.getDosage()
        );
        medicine.setInteraction(
                updated.getInteraction()
        );
        medicine.setContraindications(
                updated.getContraindications()
        );
        medicine.setSideEffects(
                updated.getSideEffects()
        );
        medicine.setPregnancyLactation(
                updated.getPregnancyLactation()
        );
        medicine.setPrecautionsWarnings(
                updated.getPrecautionsWarnings()
        );
        medicine.setSpecialPopulations(
                updated.getSpecialPopulations()
        );
        medicine.setOverdoseEffects(
                updated.getOverdoseEffects()
        );
        medicine.setReconstitution(
                updated.getReconstitution()
        );
        medicine.setStorageConditions(
                updated.getStorageConditions()
        );
        medicine.setChemicalStructure(
                updated.getChemicalStructure()
        );
        return medicineRepository.save(medicine);
    }
}