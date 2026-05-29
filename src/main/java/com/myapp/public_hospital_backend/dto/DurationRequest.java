package com.myapp.public_hospital_backend.dto;

public class DurationRequest {
    private String nationalId;
    private String duration;

    public DurationRequest() {
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
