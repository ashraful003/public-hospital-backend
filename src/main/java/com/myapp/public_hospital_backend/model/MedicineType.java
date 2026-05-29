package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicine_type")
public class MedicineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medicine_type", nullable = false)
    private String medicineType;
    @Column(name = "national_id", nullable = false)
    private String nationalId;

    public Long getId() {
        return id;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
