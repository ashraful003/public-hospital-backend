package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "blood")
public class BloodProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String name;

    private String address;

    private String phoneNumber;

    private String bloodGroup;

    private LocalDate lastDonateDate;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getLastDonateDate() {
        return lastDonateDate;
    }

    public void setLastDonateDate(LocalDate lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
    }
}