package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dose_time",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dose_time", "national_id"}))
public class DoseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dose_time", nullable = false)
    private String doseTime;
    @Column(name = "national_id", nullable = false)
    private String nationalId;

    public Long getId() {
        return id;
    }

    public String getDoseTime() {
        return doseTime;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDoseTime(String doseTime) {
        this.doseTime = doseTime;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}