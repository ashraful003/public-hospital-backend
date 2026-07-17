package com.myapp.public_hospital_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inpatient_bills")
public class InpatientBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long admissionId;
    @Column(nullable = false)
    private Long patientId;
    @Column(nullable = false)
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String seatNo;
    private String seatType;
    private Integer totalDays;
    @Column(nullable = false)
    private Double bedCharge = 0.0;
    @Column(nullable = false)
    private Double doctorCharge = 0.0;
    @Column(nullable = false)
    private Double operationCharge = 0.0;
    @Column(nullable = false)
    private Double medicineCharge = 0.0;
    @Column(nullable = false)
    private Double pathologyCharge = 0.0;
    @Column(nullable = false)
    private Double radiologyCharge = 0.0;
    @Column(nullable = false)
    private Double nursingCharge = 0.0;
    @Column(nullable = false)
    private Double oxygenCharge = 0.0;
    @Column(nullable = false)
    private Double otherCharge = 0.0;
    @Column(nullable = false)
    private Double discount = 0.0;
    @Column(nullable = false)
    private Double vat = 0.0;
    @Column(nullable = false)
    private Double subtotal = 0.0;
    @Column(nullable = false)
    private Double grandTotal = 0.0;
    @Column(nullable = false)
    private Double paidAmount = 0.0;
    @Column(nullable = false)
    private Double dueAmount = 0.0;
    @Column(nullable = false)
    private String paymentStatus = "UNPAID";
    private String paymentMethod;
    private Long paidById;
    private String paidByName;
    private LocalDateTime paymentDate;
    @Column(nullable = false)
    private String billStatus = "DRAFT";
    private LocalDateTime billDate;
    private Long createdById;
    private String createdByName;

    public InpatientBill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Double getBedCharge() {
        return bedCharge;
    }

    public void setBedCharge(Double bedCharge) {
        this.bedCharge = bedCharge;
    }

    public Double getDoctorCharge() {
        return doctorCharge;
    }

    public void setDoctorCharge(Double doctorCharge) {
        this.doctorCharge = doctorCharge;
    }

    public Double getOperationCharge() {
        return operationCharge;
    }

    public void setOperationCharge(Double operationCharge) {
        this.operationCharge = operationCharge;
    }

    public Double getMedicineCharge() {
        return medicineCharge;
    }

    public void setMedicineCharge(Double medicineCharge) {
        this.medicineCharge = medicineCharge;
    }

    public Double getPathologyCharge() {
        return pathologyCharge;
    }

    public void setPathologyCharge(Double pathologyCharge) {
        this.pathologyCharge = pathologyCharge;
    }

    public Double getRadiologyCharge() {
        return radiologyCharge;
    }

    public void setRadiologyCharge(Double radiologyCharge) {
        this.radiologyCharge = radiologyCharge;
    }

    public Double getNursingCharge() {
        return nursingCharge;
    }

    public void setNursingCharge(Double nursingCharge) {
        this.nursingCharge = nursingCharge;
    }

    public Double getOxygenCharge() {
        return oxygenCharge;
    }

    public void setOxygenCharge(Double oxygenCharge) {
        this.oxygenCharge = oxygenCharge;
    }

    public Double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Double otherCharge) {
        this.otherCharge = otherCharge;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaidById() {
        return paidById;
    }

    public void setPaidById(Long paidById) {
        this.paidById = paidById;
    }

    public String getPaidByName() {
        return paidByName;
    }

    public void setPaidByName(String paidByName) {
        this.paidByName = paidByName;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}