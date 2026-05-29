package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doses",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dose", "national_id"}))
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dose;
    @Column(name = "national_id", nullable = false)
    private String nationalId;

    public Dose() {
    }

    public Dose(Long id, String dose, String nationalId) {
        this.id = id;
        this.dose = dose;
        this.nationalId = nationalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}