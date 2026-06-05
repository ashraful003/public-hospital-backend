package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.model.Bill;
import com.myapp.public_hospital_backend.model.Invoice;
import com.myapp.public_hospital_backend.model.Prescription;
import com.myapp.public_hospital_backend.model.Test;
import com.myapp.public_hospital_backend.repository.BillRepository;
import com.myapp.public_hospital_backend.repository.InvoiceRepository;
import com.myapp.public_hospital_backend.repository.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillService {
    private final BillRepository billRepository;
    private final TestRepository testRepository;
    private final InvoiceRepository invoiceRepository;
    private final ObjectMapper objectMapper =
            new ObjectMapper();

    public BillService(
            BillRepository billRepository,
            TestRepository testRepository,
            InvoiceRepository invoiceRepository
    ) {
        this.billRepository = billRepository;
        this.testRepository = testRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Bill createBillFromPrescription(
            Prescription prescription,
            List<String> testNames
    ) {
        if (testNames == null || testNames.isEmpty()) {
            return null;
        }
        List<Test> selectedTests = new ArrayList<>();
        BigDecimal totalBill = BigDecimal.ZERO;
        for (String testName : testNames) {
            if (testName == null || testName.trim().isEmpty()) {
                continue;
            }
            Test test = testRepository
                    .findFirstByTestNameIgnoreCase(testName.trim())
                    .orElse(null);
            if (test != null) {
                selectedTests.add(test);
                totalBill = totalBill.add(
                        BigDecimal.valueOf(test.getPrice())
                );
            }
        }
        if (selectedTests.isEmpty()) {
            return null;
        }
        try {
            Bill bill = new Bill();
            bill.setPrescriptionId(prescription.getId());
            bill.setDoctorName(prescription.getDoctorName());
            bill.setPatientId(prescription.getPatientId());
            bill.setPatientName(prescription.getPatientName());
            bill.setPatientAge(prescription.getPatientAge());
            bill.setPatientWeight(prescription.getPatientWeight());
            bill.setSelectedTests(objectMapper.writeValueAsString(selectedTests));
            bill.setTotalBill(totalBill);
            bill.setDiscountAmount(BigDecimal.ZERO);
            bill.setTotalPay(BigDecimal.ZERO);
            bill.setRefundAmount(BigDecimal.ZERO);
            bill.setTotalDue(totalBill);
            bill.setAccountantName(null);
            bill.setAccountantId(null);
            bill.setPaymentDateTime(null);
            bill.setStatus("UNPAID");
            bill.setTestStatus("PENDING");
            bill.setCreatedDate(LocalDateTime.now());
            return billRepository.save(bill);
        } catch (Exception e) {
            throw new RuntimeException("Bill creation failed: " + e.getMessage());
        }
    }

    public List<Bill> getPatientBills(
            String patientId
    ) {
        return billRepository.findByPatientId(
                patientId
        );
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBill(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    @Transactional
    public void deleteExpiredUnpaidBills() {
        List<Bill> expiredBills =
                billRepository.findAll()
                        .stream()
                        .filter(bill ->
                                bill.getStatus() != null &&
                                        bill.getCreatedDate() != null &&
                                        bill.getStatus().equalsIgnoreCase("UNPAID") &&
                                        bill.getCreatedDate()
                                                .plusDays(30)
                                                .isBefore(LocalDateTime.now())
                        )
                        .toList();
        if (expiredBills.isEmpty()) {
            System.out.println("No expired unpaid bills found");
            return;
        }
        billRepository.deleteAll(expiredBills);
        System.out.println("AUTO-DELETED bills: " + expiredBills.size());
    }

    public Invoice updatePayment(
            Long billId,
            BigDecimal payAmount,
            BigDecimal discountAmount,
            String accountantName,
            String accountantId
    ) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        BigDecimal totalBill = bill.getTotalBill();
        BigDecimal discount = discountAmount != null
                ? discountAmount
                : BigDecimal.ZERO;
        BigDecimal afterDiscount = totalBill.subtract(discount);
        BigDecimal previousPaid = bill.getTotalPay() != null
                ? bill.getTotalPay()
                : BigDecimal.ZERO;
        BigDecimal payment = payAmount != null
                ? payAmount
                : BigDecimal.ZERO;
        BigDecimal newPaid = previousPaid.add(payment);
        BigDecimal due = afterDiscount.subtract(newPaid);
        if (due.compareTo(BigDecimal.ZERO) < 0) {
            due = BigDecimal.ZERO;
        }
        bill.setTotalPay(newPaid);
        bill.setTotalDue(due);
        bill.setDiscountAmount(discountAmount);
        bill.setAccountantName(accountantName);
        bill.setAccountantId(accountantId);
        bill.setPaymentDateTime(LocalDateTime.now());
        if (due.compareTo(BigDecimal.ZERO) == 0) {
            bill.setStatus("PAID");
        } else if (newPaid.compareTo(BigDecimal.ZERO) > 0) {
            bill.setStatus("PARTIAL");
        } else {
            bill.setStatus("UNPAID");
        }
        billRepository.save(bill);
        Invoice invoice = new Invoice();
        invoice.setBillId(bill.getId());
        invoice.setPrescriptionId(bill.getPrescriptionId());
        invoice.setPatientId(bill.getPatientId());
        invoice.setPatientName(bill.getPatientName());
        invoice.setPatientAge(bill.getPatientAge());
        invoice.setAccountantName(accountantName);
        invoice.setAccountantId(accountantId);
        invoice.setSelectedTests(bill.getSelectedTests());
        invoice.setTotalBill(totalBill);
        invoice.setDiscountAmount(discount);
        invoice.setTotalAfterDiscount(afterDiscount);
        invoice.setPaidAmount(newPaid);
        invoice.setDueAmount(due);
        if (due.compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus("PAID");
        } else if (newPaid.compareTo(BigDecimal.ZERO) > 0) {
            invoice.setStatus("PARTIAL");
        } else {
            invoice.setStatus("UNPAID");
        }
        invoice.setPaymentDatetime(LocalDateTime.now());
        invoice.setCreatedAt(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }
}