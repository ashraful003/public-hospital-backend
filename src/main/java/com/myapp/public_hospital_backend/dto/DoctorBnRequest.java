package com.myapp.public_hospital_backend.dto;

public class DoctorBnRequest {

    private String doctorBnName;
    private String doctorBnId;
    private String doctorBnDegree;
    private String doctorBnSpecialist;
    private String doctorBnInstitute;
    private String doctorBnLicense;
    private String doctorBnVisitingTime;

    public DoctorBnRequest() {
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
