package com.myapp.public_hospital_backend.dto;

public class DoseRequest {
    private String dose;
    private String nationalId;

    public DoseRequest() {
    }

    public DoseRequest(String dose, String nationalId) {
        this.dose = dose;
        this.nationalId = nationalId;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
