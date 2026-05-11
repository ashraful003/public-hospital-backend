package com.myapp.public_hospital_backend.controller;

import com.myapp.public_hospital_backend.dto.*;
import com.myapp.public_hospital_backend.model.Attendance;
import com.myapp.public_hospital_backend.model.BloodProfile;
import com.myapp.public_hospital_backend.model.Medicine;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final OtpService otpService;
    private final PasswordService passwordService;
    private final UserService userService;
    private final BloodProfileService service;
    private final AttendanceService attendanceService;
    private final MedicineService medicineService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email is required"));
        }
        if (!authService.isUserRegistered(email)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }
        String message = otpService.sendOtp(email);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        if (email == null || code == null || email.isEmpty() || code.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email and OTP code are required"));
        }
        try {
            otpService.verifyOtp(email, code);
            return ResponseEntity.ok(Map.of("message", "OTP verified successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String response = passwordService.changePassword(request);
            return ResponseEntity.ok(Map.of("message", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
        try {
            User user = userService.getCurrentUserByEmail(email);
            UserProfileResponse res = new UserProfileResponse();
            res.setNationalId(user.getNationalId());
            res.setName(user.getName());
            res.setEmail(user.getEmail());
            res.setDob(user.getDob());
            res.setPhone(user.getPhone());
            res.setAddress(user.getAddress());
            res.setDegree(user.getDegree());
            res.setInstitute(user.getInstitute());
            res.setLicense(user.getLicense());
            res.setSpecialist(user.getSpecialist());
            res.setWeight(user.getWeight());
            res.setRole(user.getRole().name());
            if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                res.setImageUrl(user.getImageUrl());
            } else if (user.getImageUrl() != null) {
                String fileName = System.currentTimeMillis() + ".jpg";
                byte[] imageBytes = java.util.Base64.getDecoder()
                        .decode(user.getImageUrl());
                Path uploadDir = Paths.get("uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                Files.write(uploadDir.resolve(fileName), imageBytes);
                String imageUrl = "/uploads/" + fileName;
                user.setImageUrl(imageUrl);
                userService.save(user);
                res.setImageUrl(imageUrl);
            } else {
                res.setImageUrl(null);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request) {
        try {
            User user = userService.getCurrentUserByEmail(request.getEmail());
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            user.setAddress(request.getAddress());
            user.setWeight(request.getWeight());
            user.setInstitute(request.getInstitute());
            user.setDegree(request.getDegree());
            user.setLicense(request.getLicense());
            user.setSpecialist(request.getSpecialist());
            if (request.getDob() != null && !request.getDob().isEmpty()) {
                user.setDob(java.time.LocalDate.parse(request.getDob()));
            }
            User savedUser = userService.save(user);
            UserProfileResponse res = new UserProfileResponse();
            res.setNationalId(savedUser.getNationalId());
            res.setName(savedUser.getName());
            res.setEmail(savedUser.getEmail());
            res.setPhone(savedUser.getPhone());
            res.setAddress(savedUser.getAddress());
            res.setDob(savedUser.getDob());
            res.setWeight(savedUser.getWeight());
            res.setInstitute(savedUser.getInstitute());
            res.setDegree(savedUser.getDegree());
            res.setLicense(savedUser.getLicense());
            res.setSpecialist(savedUser.getSpecialist());
            res.setRole(savedUser.getRole().name());
            return ResponseEntity.ok(Map.of(
                    "message", "Profile updated successfully",
                    "data", res
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/update-profile-image-json")
    public ResponseEntity<?> uploadImage(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String base64 = request.get("imageBase64");
            if (email == null || base64 == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Email & image required"));
            }
            User user = userService.getCurrentUserByEmail(email);
            if (user == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "User not found"));
            }
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }
            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64);
            String fileName = System.currentTimeMillis() + ".jpg";
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Files.write(uploadDir.resolve(fileName), imageBytes);
            userService.save(user);
            return ResponseEntity.ok(Map.of(
                    "message", "Upload success"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/change-old-password")
    public ResponseEntity<?> changeOldPassword(@RequestBody ChangeOldPasswordRequest request) {
        try {
            String response = passwordService.changeOldPassword(request);
            return ResponseEntity.ok(Map.of("message", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<BloodProfile> getByEmail(@PathVariable String email) {
        return service.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/blood")
    public ResponseEntity<BloodProfile> create(@RequestBody BloodProfile profile) {
        return ResponseEntity.ok(service.save(profile));
    }

    @PutMapping("/{email}")
    public ResponseEntity<BloodProfile> update(
            @PathVariable String email,
            @RequestBody BloodProfile profile) {
        return ResponseEntity.ok(service.update(email, profile));
    }

    @GetMapping("blood-list")
    public ResponseEntity<List<BloodProfile>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/all-doctor")
    public List<User> getAllDoctors() {
        return userService.getAllDoctors();
    }

    @GetMapping("/active-doctor")
    public List<User> getActiveDoctors() {
        return userService.getActiveDoctors();
    }

    @GetMapping("/all-assistant")
    public List<User> getAllDoctorsAssistant() {
        return userService.getAllDoctorsAssistant();
    }

    @GetMapping("/active-assistant")
    public List<User> getActiveDoctorsAssistant() {
        return userService.getActiveDoctorsAssistant();
    }

    @GetMapping("/all-nurses")
    public List<User> getAllNurses() {
        return userService.getAllNurses();
    }

    @GetMapping("/active-nurses")
    public List<User> getActiveNurses() {
        return userService.getActiveNurses();
    }

    @GetMapping("/all-accountants")
    public List<User> getAllAccountants() {
        return userService.getAllAccountants();
    }

    @GetMapping("/active-accountants")
    public List<User> getActiveAccountants() {
        return userService.getActiveAccountants();
    }

    @GetMapping("/all-pharmacists")
    public List<User> getAllPharmacist() {
        return userService.getAllPharmacist();
    }

    @GetMapping("/active-pharmacists")
    public List<User> getActivePharmacist() {
        return userService.getActivePharmacist();
    }

    @GetMapping("/all-receptionists")
    public List<User> getAllReceptionist() {
        return userService.getAllReceptionist();
    }

    @GetMapping("/active-receptionists")
    public List<User> getActiveReceptionist() {
        return userService.getActiveReceptionist();
    }

    @GetMapping("/all-drivers")
    public List<User> getAllDriver() {
        return userService.getAllDriver();
    }

    @GetMapping("/active-drivers")
    public List<User> getActiveDriver() {
        return userService.getActiveDriver();
    }

    @GetMapping("all-cleaners")
    public List<User> getAllCleaner() {
        return userService.getAllCleaner();
    }

    @GetMapping("active-cleaners")
    public List<User> getActiveCleaners() {
        return userService.getActiveCleaner();
    }

    @GetMapping("/user/{nationalId}")
    public ResponseEntity<?> getUser(@PathVariable String nationalId) {
        try {
            User user = attendanceService.findUser(nationalId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/check-in/{nationalId}")
    public ResponseEntity<?> checkIn(@PathVariable String nationalId) {
        try {
            String msg = attendanceService.checkIn(nationalId);
            return ResponseEntity.ok(Map.of("message", msg));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/check-out/{nationalId}")
    public ResponseEntity<?> checkOut(@PathVariable String nationalId) {
        try {
            String msg = attendanceService.checkOut(nationalId);
            return ResponseEntity.ok(Map.of("message", msg));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/all-attendance")
    public ResponseEntity<?> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/my/{nationalId}")
    public List<Attendance> getMyAttendance(@PathVariable String nationalId) {
        return attendanceService.getMyAttendance(nationalId);
    }

    @PostMapping("/register-pharmaceutical")
    public ResponseEntity<?> pharmaceuticalRegister(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.pharmaceuticalRegister(request);
            return ResponseEntity.ok(Map.of(
                    "message", response.getMessage(),
                    "accessToken", response.getAccessToken(),
                    "refreshToken", response.getRefreshToken(),
                    "role", response.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("all-pharmaceuticals")
    public List<User> getAllPharmaceutical() {
        return userService.getAllPharmaceutical();
    }

    @DeleteMapping("/delete-pharmaceutical/{id}")
    public ResponseEntity<?> deletePharmaceutical(@PathVariable Long id) {
        userService.deletePharmaceutical(id);
        return ResponseEntity.ok("Pharmaceutical deleted successfully");
    }

    @PostMapping("/add-medicine")
    public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine) {
        Medicine savedMedicine = medicineService.addMedicine(medicine);
        return ResponseEntity.ok("Medicine added successfully");
    }

    @GetMapping("/all-my-medicine")
    public ResponseEntity<List<Medicine>> getMyMedicines(
            @RequestParam String name) {
        return ResponseEntity.ok(
                medicineService.getMyMedicines(name)
        );
    }

    @GetMapping("/all-medicine")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @PutMapping("/update/medicine/{id}")
    public ResponseEntity<?> updateMedicine(
            @PathVariable Long id,
            @RequestBody Medicine medicine) {
        medicineService.updateMedicine(id, medicine);
        return ResponseEntity.ok("Medicine updated successfully");
    }

    @DeleteMapping("/delete/medicine/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}