package com.myapp.public_hospital_backend.dto;

import java.time.LocalDateTime;

public class InpatientBillRequest {

    private Integer totalDays;

    private Double bedCharge;
    private Double doctorCharge;
    private Double operationCharge;
    private Double medicineCharge;
    private Double pathologyCharge;
    private Double radiologyCharge;
    private Double nursingCharge;
    private Double oxygenCharge;
    private Double otherCharge;

    private Double discount;
    private Double vat;

    private Double subtotal;
    private Double grandTotal;

    private Double paidAmount;
    private Double dueAmount;

    private String paymentStatus;
    private String paymentMethod;

    private Long paidById;
    private String paidByName;

    private LocalDateTime paymentDate;

    private String billStatus;
    private LocalDateTime billDate;

    private Long createdById;
    private String createdByName;

    public InpatientBillRequest() {
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