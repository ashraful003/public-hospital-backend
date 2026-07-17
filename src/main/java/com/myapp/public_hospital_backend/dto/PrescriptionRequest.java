package com.myapp.public_hospital_backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PrescriptionRequest {

    // Doctor info (English)
    private String doctorName;
    private String doctorDegree;
    private String doctorSpecialist;
    private String doctorInstitute;
    private String doctorLicense;

    // Doctor info (Bengali)
    private String doctorBnName;
    private String doctorBnDegree;
    private String doctorBnSpecialist;
    private String doctorBnInstitute;
    private String doctorBnLicense;
    private String doctorBnVisitingTime;

    // Patient info
    private String patientId;
    private String patientName;
    private String patientAge;
    private String patientWeight;

    // Clinical info
    private String problems;
    private String bloodPressure;
    private String pulse;
    private String temperature;
    private List<Map<String, Object>> medicines;
    private String advice;
    private List<String> tests;
    private String nextMeet;
    private LocalDateTime date;

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

    public String getDoctorBnName() {
        return doctorBnName;
    }

    public void setDoctorBnName(String doctorBnName) {
        this.doctorBnName = doctorBnName;
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

    public List<Map<String, Object>> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Map<String, Object>> medicines) {
        this.medicines = medicines;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
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