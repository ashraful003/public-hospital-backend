package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.UserProfileResponse;
import com.myapp.public_hospital_backend.dto.UserResponse;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.model.UserRole;
import com.myapp.public_hospital_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getCurrentUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllDoctors() {
        return userRepository.findByRole(UserRole.DOCTOR);
    }

    public List<User> getActiveDoctors() {
        return userRepository.findByRoleAndIsActive(UserRole.DOCTOR, true);
    }

    public List<User> getAllDoctorsAssistant() {
        return userRepository.findByRole(UserRole.DOCTOR_ASSISTANT);
    }

    public List<User> getActiveDoctorsAssistant() {
        return userRepository.findByRoleAndIsActive(UserRole.DOCTOR_ASSISTANT, true);
    }

    public List<User> getAllNurses() {
        return userRepository.findByRole(UserRole.NURSE);
    }

    public List<User> getActiveNurses() {
        return userRepository.findByRoleAndIsActive(UserRole.NURSE, true);
    }

    public List<User> getAllAccountants() {
        return userRepository.findByRole(UserRole.ACCOUNTANT);
    }

    public List<User> getActiveAccountants() {
        return userRepository.findByRoleAndIsActive(UserRole.ACCOUNTANT, true);
    }

    public List<User> getAllPharmacist() {
        return userRepository.findByRole(UserRole.PHARMACIST);
    }

    public List<User> getActivePharmacist() {
        return userRepository.findByRoleAndIsActive(UserRole.PHARMACIST, true);
    }

    public List<User> getAllReceptionist() {
        return userRepository.findByRole(UserRole.RECEPTIONIST);
    }

    public List<User> getActiveReceptionist() {
        return userRepository.findByRoleAndIsActive(UserRole.RECEPTIONIST, true);
    }

    public List<User> getAllDriver() {
        return userRepository.findByRole(UserRole.DRIVER);
    }

    public List<User> getActiveDriver() {
        return userRepository.findByRoleAndIsActive(UserRole.DRIVER, true);
    }

    public List<User> getAllCleaner() {
        return userRepository.findByRole(UserRole.CLEANER);
    }

    public List<User> getActiveCleaner() {
        return userRepository.findByRoleAndIsActive(UserRole.CLEANER, true);
    }

    public List<User> getAllPharmaceutical() {
        return userRepository.findByRole(UserRole.PHARMACEUTICAL);
    }

    public void deletePharmaceutical(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmaceutical not found"));
        if (user.getRole() != UserRole.PHARMACEUTICAL) {
            throw new RuntimeException("User is not a pharmaceutical");
        }
        userRepository.delete(user);
    }

    public UserProfileResponse getUserByNationalId(String nationalId) {

        User user = userRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserProfileResponse response = new UserProfileResponse();
        response.setNationalId(user.getNationalId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setDob(user.getDob());
        response.setWeight(user.getWeight());
        response.setImageUrl(user.getImageUrl());
        response.setInstitute(user.getInstitute());
        response.setDegree(user.getDegree());
        response.setLicense(user.getLicense());
        response.setSpecialist(user.getSpecialist());
        response.setRole(user.getRole().name());
        return response;
    }

    public List<User> getAllDiagnosticCenter() {
        return userRepository.findByRole(UserRole.DIAGNOSTIC_CENTER);
    }

    public void deleteDiagnosticCenter(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostic Center not found"));
        if (user.getRole() != UserRole.DIAGNOSTIC_CENTER) {
            throw new RuntimeException("User is not a Diagnostic Center");
        }
        userRepository.delete(user);
    }
}