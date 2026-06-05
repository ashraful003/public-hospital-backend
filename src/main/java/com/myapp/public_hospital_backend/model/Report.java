package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long billId;
    private String centerName;
    private String centerAddress;
    private String patientId;
    private String patientName;
    private String patientAge;
    private String patientWeight;
    private String doctorName;
    private String labNo;
    private String sampleDate;
    private String reviewDate;
    private String reportDate;
    private String testStatus;
    @Column(columnDefinition = "TEXT")
    private String tests;

    public Long getId() {
        return id;
    }

    public Long getBillId() {
        return billId;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getLabNo() {
        return labNo;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public String getTests() {
        return tests;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }
}