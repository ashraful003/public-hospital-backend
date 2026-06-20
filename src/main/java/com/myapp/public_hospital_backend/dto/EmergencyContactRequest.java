package com.myapp.public_hospital_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class EmergencyContactRequest {
    @NotBlank(message = "Emergency number is required")
    private String emergencyNumber;
    @NotBlank(message = "Emergency doctor number is required")
    private String emergencyDoctorNumber;
    @NotBlank(message = "Emergency doctor whatsapp number is required")
    private String emergencyDoctorWhatsappNumber;

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