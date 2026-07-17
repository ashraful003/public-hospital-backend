package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.InpatientBillRequest;
import com.myapp.public_hospital_backend.model.HospitalAdmission;
import com.myapp.public_hospital_backend.model.HospitalSeat;
import com.myapp.public_hospital_backend.model.InpatientBill;
import com.myapp.public_hospital_backend.repository.InpatientBillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InpatientBillService {
    private final InpatientBillRepository inpatientBillRepository;

    public InpatientBillService(InpatientBillRepository inpatientBillRepository) {
        this.inpatientBillRepository = inpatientBillRepository;
    }

    public InpatientBill generateBillForAdmission(HospitalAdmission admission, HospitalSeat seat) {
        if (inpatientBillRepository.existsByAdmissionId(admission.getId())) {
            return inpatientBillRepository.findByAdmissionId(admission.getId()).get();
        }
        InpatientBill bill = new InpatientBill();
        bill.setAdmissionId(admission.getId());
        bill.setPatientId(admission.getPatientId());
        bill.setPatientName(admission.getPatientName());
        bill.setDoctorId(admission.getDoctorId());
        bill.setDoctorName(admission.getDoctorName());
        bill.setSeatNo(seat.getSeatNo());
        bill.setSeatType(seat.getType());
        int initialDays = 1;
        double bedChargePerDay = seat.getPrice() != null ? seat.getPrice() : 0.0;
        bill.setTotalDays(initialDays);
        bill.setBedCharge(bedChargePerDay * initialDays);
        bill.setDoctorCharge(0.0);
        bill.setOperationCharge(0.0);
        bill.setMedicineCharge(0.0);
        bill.setPathologyCharge(0.0);
        bill.setRadiologyCharge(0.0);
        bill.setNursingCharge(0.0);
        bill.setOxygenCharge(0.0);
        bill.setOtherCharge(0.0);
        bill.setDiscount(0.0);
        bill.setVat(0.0);
        bill.setPaidAmount(0.0);
        bill.setPaymentStatus("UNPAID");
        bill.setPaidById(null);
        bill.setPaidByName(null);
        bill.setPaymentDate(null);
        bill.setBillStatus("DRAFT");
        bill.setBillDate(LocalDateTime.now());
        bill.setCreatedById(admission.getAdmitedById());
        bill.setCreatedByName(admission.getAdmitedByName());
        recalculateTotals(bill);
        return inpatientBillRepository.save(bill);
    }

    public List<InpatientBill> getAllBills() {
        return inpatientBillRepository.findAll();
    }

    public InpatientBill getBillById(Long id) {
        return inpatientBillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inpatient bill not found with id: " + id));
    }

    public List<InpatientBill> getBillsByPatientId(Long patientId) {
        return inpatientBillRepository.findByPatientId(patientId);
    }

    public InpatientBill updateBill(Long id, InpatientBillRequest request) {
        InpatientBill bill = getBillById(id);
        bill.setTotalDays(request.getTotalDays());
        bill.setBedCharge(request.getBedCharge());
        bill.setDoctorCharge(request.getDoctorCharge());
        bill.setOperationCharge(request.getOperationCharge());
        bill.setMedicineCharge(request.getMedicineCharge());
        bill.setPathologyCharge(request.getPathologyCharge());
        bill.setRadiologyCharge(request.getRadiologyCharge());
        bill.setNursingCharge(request.getNursingCharge());
        bill.setOxygenCharge(request.getOxygenCharge());
        bill.setOtherCharge(request.getOtherCharge());
        bill.setDiscount(request.getDiscount());
        bill.setVat(request.getVat());
        bill.setPaidAmount(request.getPaidAmount());
        bill.setPaymentMethod(request.getPaymentMethod());
        bill.setPaidById(request.getPaidById());
        bill.setPaidByName(request.getPaidByName());
        bill.setPaymentDate(request.getPaymentDate());
        bill.setCreatedById(request.getCreatedById());
        bill.setCreatedByName(request.getCreatedByName());
        recalculateTotals(bill);
        return inpatientBillRepository.save(bill);
    }

    private void recalculateTotals(InpatientBill bill) {
        double subtotal = safe(bill.getBedCharge())
                + safe(bill.getDoctorCharge())
                + safe(bill.getOperationCharge())
                + safe(bill.getMedicineCharge())
                + safe(bill.getPathologyCharge())
                + safe(bill.getRadiologyCharge())
                + safe(bill.getNursingCharge())
                + safe(bill.getOxygenCharge())
                + safe(bill.getOtherCharge());
        double grandTotal = subtotal - safe(bill.getDiscount()) + safe(bill.getVat());
        if (grandTotal < 0) grandTotal = 0.0;
        double due = grandTotal - safe(bill.getPaidAmount());
        if (due < 0) due = 0.0;
        bill.setSubtotal(subtotal);
        bill.setGrandTotal(grandTotal);
        bill.setDueAmount(due);
        if (safe(bill.getPaidAmount()) <= 0) {
            bill.setPaymentStatus("UNPAID");
        } else if (due <= 0) {
            bill.setPaymentStatus("PAID");
        } else {
            bill.setPaymentStatus("PARTIAL");
        }
        if (due <= 0 && safe(bill.getPaidAmount()) > 0) {
            bill.setBillStatus("PAID");
        }
    }

    private double safe(Double value) {
        return value != null ? value : 0.0;
    }


}