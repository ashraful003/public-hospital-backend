package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.ReportRequest;
import com.myapp.public_hospital_backend.model.Bill;
import com.myapp.public_hospital_backend.model.Report;
import com.myapp.public_hospital_backend.repository.BillRepository;
import com.myapp.public_hospital_backend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository repository;
    @Autowired
    private BillRepository billRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Report createReport(ReportRequest request) {
        Report report = new Report();
        report.setBillId(request.getBillId());
        report.setCenterName(request.getCenterName());
        report.setCenterAddress(request.getCenterAddress());
        report.setPatientId(request.getPatientId());
        report.setPatientName(request.getPatientName());
        report.setPatientAge(request.getPatientAge());
        report.setPatientWeight(request.getPatientWeight());
        report.setDoctorName(request.getDoctorName());
        report.setLabNo(request.getLabNo());
        report.setSampleDate(request.getSampleDate());
        report.setReviewDate(request.getReviewDate());
        report.setReportDate(request.getReportDate());
        report.setTestStatus(request.getTestStatus());
        try {
            report.setTests(objectMapper.writeValueAsString(request.getTests()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert tests to JSON", e);
        }
        Report savedReport = repository.save(report);
        if (request.getBillId() != null) {
            Bill bill = billRepository
                    .findById(request.getBillId())
                    .orElseThrow(() -> new RuntimeException("Bill not found"));
            bill.setTestStatus(request.getTestStatus());
            billRepository.save(bill);
        }
        return savedReport;
    }

    public List<Report> getAllReports() {
        return repository.findAll();
    }

    public List<Report> getByPatientId(String patientId) {
        return repository.findByPatientId(patientId);
    }

    public List<Report> getByCenterName(String centerName) {
        return repository.findByCenterName(centerName);
    }

    public Report getReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }

    public Report updateReport(Long id, ReportRequest request) {
        Report report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.setCenterName(request.centerName);
        report.setCenterAddress(request.centerAddress);
        report.setPatientId(request.patientId);
        report.setPatientName(request.patientName);
        report.setPatientAge(request.patientAge);
        report.setPatientWeight(request.patientWeight);
        report.setDoctorName(request.doctorName);
        report.setLabNo(request.labNo);
        report.setSampleDate(request.sampleDate);
        report.setReviewDate(request.reviewDate);
        report.setReportDate(request.reportDate);
        report.setTestStatus(request.testStatus);
        try {
            report.setTests(objectMapper.writeValueAsString(request.tests));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert tests", e);
        }
        Report updated = repository.save(report);
        if (request.getBillId() != null) {
            Bill bill = billRepository
                    .findById(request.getBillId())
                    .orElseThrow(() -> new RuntimeException("Bill not found"));
            bill.setTestStatus(request.getTestStatus());
            billRepository.save(bill);
        }
        return updated;
    }
}