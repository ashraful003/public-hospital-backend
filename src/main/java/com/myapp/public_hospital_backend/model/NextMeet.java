package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "next_meet")
public class NextMeet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "national_id", nullable = false)
    private String nationalId;
    @Column(nullable = false)
    private String duration;

    public NextMeet() {
    }

    public NextMeet(String nationalId, String duration) {
        this.nationalId = nationalId;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}