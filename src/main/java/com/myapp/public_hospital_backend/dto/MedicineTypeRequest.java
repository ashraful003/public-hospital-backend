package com.myapp.public_hospital_backend.dto;

public class MedicineTypeRequest {
    private String medicineType;
    private String nationalId;

    public String getMedicineType() {
        return medicineType;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
