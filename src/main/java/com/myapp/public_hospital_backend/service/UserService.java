package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.UserResponse;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.model.UserRole;
import com.myapp.public_hospital_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public UserResponse getCurrentUserResponse() {
        return mapToResponse(getCurrentUser());
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
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
}