package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_parking")
public class UserParking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patient_id", nullable = false)
    private String patientId;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "vehicle_no", nullable = false)
    private String vehicleNo;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id", nullable = false)
    private Parking parking;
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    @Column(name = "exit_time")
    private LocalDateTime exitTime;
    @Column(name = "parking_fee")
    private Double parkingFee;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "floor")
    private String floor;
    @Column(name = "parking_no")
    private String parkingNo;
    @Column(name = "total_hours")
    private Long totalHours;
    @Column(name = "status")
    private String status = "ACTIVE";

    public UserParking() {
    }

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Parking getParking() {
        return parking;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Double getParkingFee() {
        return parkingFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getFloor() {
        return floor;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setParkingFee(Double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}