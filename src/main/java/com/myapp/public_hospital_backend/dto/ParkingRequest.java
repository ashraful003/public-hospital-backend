package com.myapp.public_hospital_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParkingRequest {
    @NotBlank
    private String floor;
    @NotBlank
    private String parkingNo;
    @NotNull
    private Double parkingFee;
    private Boolean isActive = true;

    public ParkingRequest() {
    }

    public String getFloor() {
        return floor;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public Double getParkingFee() {
        return parkingFee;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public void setParkingFee(Double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}