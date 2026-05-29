package com.myapp.public_hospital_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private String doctorDegree;
    private String doctorSpecialist;
    private String doctorInstitute;
    private String doctorLicense;
    private String patientId;
    private String patientName;
    private String patientAge;
    private String patientWeight;
    @Column(columnDefinition = "TEXT")
    private String problems;
    private String bloodPressure;
    private String pulse;
    private String temperature;
    @Column(columnDefinition = "TEXT")
    private String medicines;
    @Column(columnDefinition = "TEXT")
    private String advice;
    @Column(columnDefinition = "TEXT")
    private String tests;
    private String nextMeet;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDegree() {
        return doctorDegree;
    }

    public void setDoctorDegree(String doctorDegree) {
        this.doctorDegree = doctorDegree;
    }

    public String getDoctorSpecialist() {
        return doctorSpecialist;
    }

    public void setDoctorSpecialist(String doctorSpecialist) {
        this.doctorSpecialist = doctorSpecialist;
    }

    public String getDoctorInstitute() {
        return doctorInstitute;
    }

    public void setDoctorInstitute(String doctorInstitute) {
        this.doctorInstitute = doctorInstitute;
    }

    public String getDoctorLicense() {
        return doctorLicense;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getNextMeet() {
        return nextMeet;
    }

    public void setNextMeet(String nextMeet) {
        this.nextMeet = nextMeet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}