package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.DoctorBnRequest;
import com.myapp.public_hospital_backend.model.DoctorBn;
import com.myapp.public_hospital_backend.repository.DoctorBnRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorBnService {
    private final DoctorBnRepository repository;

    public DoctorBnService(DoctorBnRepository repository) {
        this.repository = repository;
    }

    public String createDoctorBn(DoctorBnRequest request) {
        if (repository.existsByDoctorBnId(request.getDoctorBnId())) {
            throw new RuntimeException("Doctor ID already exists.");
        }
        DoctorBn doctor = new DoctorBn();
        doctor.setDoctorBnName(request.getDoctorBnName());
        doctor.setDoctorBnId(request.getDoctorBnId());
        doctor.setDoctorBnDegree(request.getDoctorBnDegree());
        doctor.setDoctorBnSpecialist(request.getDoctorBnSpecialist());
        doctor.setDoctorBnInstitute(request.getDoctorBnInstitute());
        doctor.setDoctorBnLicense(request.getDoctorBnLicense());
        doctor.setDoctorBnVisitingTime(request.getDoctorBnVisitingTime());
        repository.save(doctor);
        return "Doctor profile created successfully.";
    }

    public DoctorBn getDoctorBnById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found."));
    }

    public DoctorBn getDoctorBnByDoctorId(String doctorBnId) {
        return repository.findByDoctorBnId(doctorBnId)
                .orElseThrow(() -> new RuntimeException("Doctor not found."));
    }

    public String updateDoctorBn(Long id, DoctorBnRequest request) {
        DoctorBn doctor = getDoctorBnById(id);
        doctor.setDoctorBnName(request.getDoctorBnName());
        doctor.setDoctorBnId(request.getDoctorBnId());
        doctor.setDoctorBnDegree(request.getDoctorBnDegree());
        doctor.setDoctorBnSpecialist(request.getDoctorBnSpecialist());
        doctor.setDoctorBnInstitute(request.getDoctorBnInstitute());
        doctor.setDoctorBnLicense(request.getDoctorBnLicense());
        doctor.setDoctorBnVisitingTime(request.getDoctorBnVisitingTime());
        repository.save(doctor);
        return "Doctor profile updated successfully.";
    }
}