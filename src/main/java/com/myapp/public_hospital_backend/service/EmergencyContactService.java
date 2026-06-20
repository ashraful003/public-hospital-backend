package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.EmergencyContactRequest;
import com.myapp.public_hospital_backend.model.EmergencyContact;
import com.myapp.public_hospital_backend.repository.EmergencyContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService {
    private final EmergencyContactRepository repository;

    public EmergencyContactService(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    public EmergencyContact createEmergencyContact(
            EmergencyContactRequest request
    ) {
        try {
            if (!repository.findByEmergencyNumber(request.getEmergencyNumber()).isEmpty()) {
                throw new RuntimeException("Already exists this emergency number: " + request.getEmergencyNumber());
            }
            if (!repository.findByEmergencyDoctorNumber(request.getEmergencyDoctorNumber()).isEmpty()) {
                throw new RuntimeException("Already exists this doctor number: " + request.getEmergencyDoctorNumber());
            }
            if (!repository.findByEmergencyDoctorWhatsappNumber(request.getEmergencyDoctorWhatsappNumber()).isEmpty()) {
                throw new RuntimeException("Already exists this WhatsApp number: " + request.getEmergencyDoctorWhatsappNumber());
            }
            EmergencyContact contact = new EmergencyContact();
            contact.setEmergencyNumber(request.getEmergencyNumber());
            contact.setEmergencyDoctorNumber(request.getEmergencyDoctorNumber());
            contact.setEmergencyDoctorWhatsappNumber(request.getEmergencyDoctorWhatsappNumber());
            return repository.save(contact);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EmergencyContact updateEmergencyContact(
            Long id,
            EmergencyContactRequest request
    ) {
        EmergencyContact contact = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Emergency Contact not found"));
        try {
            contact.setEmergencyNumber(request.getEmergencyNumber());
            contact.setEmergencyDoctorNumber(request.getEmergencyDoctorNumber());
            contact.setEmergencyDoctorWhatsappNumber(request.getEmergencyDoctorWhatsappNumber());
            return repository.save(contact);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Emergency Contact: " + e.getMessage());
        }
    }

    public EmergencyContact getEmergencyContactById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Emergency Contact not found"));
    }

    public List<EmergencyContact> getAllEmergencyContacts() {
        return repository.findAll();
    }

    public void deleteEmergencyContact(Long id) {
        EmergencyContact contact = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Emergency Contact not found"));
        repository.delete(contact);
    }
}
