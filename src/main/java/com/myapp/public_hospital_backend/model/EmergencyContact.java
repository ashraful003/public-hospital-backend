package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String emergencyNumber;
    @Column(nullable = false)
    private String emergencyDoctorNumber;
    @Column(nullable = false)
    private String emergencyDoctorWhatsappNumber;

    public EmergencyContact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getEmergencyDoctorNumber() {
        return emergencyDoctorNumber;
    }

    public void setEmergencyDoctorNumber(String emergencyDoctorNumber) {
        this.emergencyDoctorNumber = emergencyDoctorNumber;
    }

    public String getEmergencyDoctorWhatsappNumber() {
        return emergencyDoctorWhatsappNumber;
    }

    public void setEmergencyDoctorWhatsappNumber(String emergencyDoctorWhatsappNumber) {
        this.emergencyDoctorWhatsappNumber = emergencyDoctorWhatsappNumber;
    }
}
