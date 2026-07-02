package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hospital_admissions")
public class HospitalAdmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long patientId;
    @Column(nullable = false)
    private String patientName;
    private Integer patientAge;
    private Double patientWeight;
    @Column(length = 500)
    private String patientAddress;
    private Long doctorId;
    private String doctorName;
    @Column(nullable = false)
    private String seatNo;
    @Column(nullable = false)
    private String admissionType;
    @Column(length = 1000)
    private String diagnosis;
    @Column(length = 1000)
    private String remarks;
    @Column(nullable = false)
    private LocalDateTime admissionDate;
    private LocalDate expectedDischargeDate;
    private LocalDateTime dischargeDate;
    @Column(nullable = false)
    private String status;
    private Long admitedById;
    private String admitedByName;
    private Long dischargedById;
    private String dischargedByName;

    public HospitalAdmission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public Double getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDateTime admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getExpectedDischargeDate() {
        return expectedDischargeDate;
    }

    public void setExpectedDischargeDate(LocalDate expectedDischargeDate) {
        this.expectedDischargeDate = expectedDischargeDate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDateTime dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAdmitedById() {
        return admitedById;
    }

    public void setAdmitedById(Long admitedById) {
        this.admitedById = admitedById;
    }

    public String getAdmitedByName() {
        return admitedByName;
    }

    public void setAdmitedByName(String admitedByName) {
        this.admitedByName = admitedByName;
    }

    public Long getDischargedById() {
        return dischargedById;
    }

    public void setDischargedById(Long dischargedById) {
        this.dischargedById = dischargedById;
    }

    public String getDischargedByName() {
        return dischargedByName;
    }

    public void setDischargedByName(String dischargedByName) {
        this.dischargedByName = dischargedByName;
    }
}