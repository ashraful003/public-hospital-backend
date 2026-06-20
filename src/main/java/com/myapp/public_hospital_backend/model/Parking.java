package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "floor", nullable = false)
    private String floor;
    @Column(name = "parking_no", nullable = false, unique = true)
    private String parkingNo;
    @Column(name = "parking_fee", nullable = false)
    private Double parkingFee;
    @Column(name = "is_active")
    private Boolean isActive = true;

    public Parking() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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