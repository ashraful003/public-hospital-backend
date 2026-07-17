package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_bn")
public class DoctorBn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String doctorBnName;
    @Column(nullable = false, unique = true)
    private String doctorBnId;
    @Column(nullable = false)
    private String doctorBnDegree;
    @Column(nullable = false)
    private String doctorBnSpecialist;
    @Column(nullable = false)
    private String doctorBnInstitute;
    @Column(nullable = false)
    private String doctorBnLicense;
    @Column(nullable = false)
    private String doctorBnVisitingTime;

    public DoctorBn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorBnName() {
        return doctorBnName;
    }

    public void setDoctorBnName(String doctorBnName) {
        this.doctorBnName = doctorBnName;
    }

    public String getDoctorBnId() {
        return doctorBnId;
    }

    public void setDoctorBnId(String doctorBnId) {
        this.doctorBnId = doctorBnId;
    }

    public String getDoctorBnDegree() {
        return doctorBnDegree;
    }

    public void setDoctorBnDegree(String doctorBnDegree) {
        this.doctorBnDegree = doctorBnDegree;
    }

    public String getDoctorBnSpecialist() {
        return doctorBnSpecialist;
    }

    public void setDoctorBnSpecialist(String doctorBnSpecialist) {
        this.doctorBnSpecialist = doctorBnSpecialist;
    }

    public String getDoctorBnInstitute() {
        return doctorBnInstitute;
    }

    public void setDoctorBnInstitute(String doctorBnInstitute) {
        this.doctorBnInstitute = doctorBnInstitute;
    }

    public String getDoctorBnLicense() {
        return doctorBnLicense;
    }

    public void setDoctorBnLicense(String doctorBnLicense) {
        this.doctorBnLicense = doctorBnLicense;
    }

    public String getDoctorBnVisitingTime() {
        return doctorBnVisitingTime;
    }

    public void setDoctorBnVisitingTime(String doctorBnVisitingTime) {
        this.doctorBnVisitingTime = doctorBnVisitingTime;
    }
}
