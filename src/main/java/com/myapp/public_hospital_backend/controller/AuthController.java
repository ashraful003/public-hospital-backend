package com.myapp.public_hospital_backend.controller;

import com.myapp.public_hospital_backend.dto.*;
import com.myapp.public_hospital_backend.model.*;
import com.myapp.public_hospital_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;
    private final OtpService otpService;
    private final PasswordService passwordService;
    private final UserService userService;
    private final BloodProfileService service;
    private final AttendanceService attendanceService;
    private final MedicineService medicineService;
    private final AdviceService advice;
    private final NextMeetService nextMeet;
    private final TestService testService;
    private final DurationService durationService;
    private final DoseService doseService;
    private final PrescriptionService prescription;
    private final MedicineTypeService medicineType;
    private final DoseTimeService doseTime;

    public AuthController(AuthService authService, OtpService otpService, PasswordService passwordService, UserService userService, BloodProfileService service, AttendanceService attendanceService, MedicineService medicineService, AdviceService advice, NextMeetService nextMeet, TestService testService, DurationService durationService, DoseService doseService, PrescriptionService prescription, MedicineTypeService medicineType, DoseTimeService doseTime) {
        this.authService = authService;
        this.otpService = otpService;
        this.passwordService = passwordService;
        this.userService = userService;
        this.service = service;
        this.attendanceService = attendanceService;
        this.medicineService = medicineService;
        this.advice = advice;
        this.nextMeet = nextMeet;
        this.testService = testService;
        this.durationService = durationService;
        this.doseService = doseService;
        this.prescription = prescription;
        this.medicineType = medicineType;
        this.doseTime = doseTime;
    }


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

    @GetMapping("/users/{nationalId}")
    public ResponseEntity<?> getUserByNationalId(
            @PathVariable String nationalId) {
        try {
            UserProfileResponse response = userService.getUserByNationalId(nationalId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "message",
                            e.getMessage()
                    )
            );
        }
    }

    @PostMapping("/add-advice")
    public Map<String, String> addAdvice(
            @RequestBody AdviceRequest request
    ) {
        advice.createAdvice(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Advice added successfully");
        return response;
    }

    @GetMapping("/doctor/advice/{nationalId}")
    public List<Advice> getByDoctor(
            @PathVariable String nationalId
    ) {
        return advice.getByNationalId(nationalId);
    }

    @PutMapping("/update-advice/{id}")
    public Map<String, String> updateAdvice(
            @PathVariable Long id,
            @RequestBody AdviceRequest request
    ) {
        advice.updateAdvice(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Advice updated successfully");
        return response;
    }

    @DeleteMapping("/delete-advice/{id}")
    public Map<String, String> deleteAdvice(
            @PathVariable Long id
    ) {
        advice.deleteAdvice(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Advice deleted successfully");
        return response;
    }

    @PostMapping("/create-meet-time")
    public Map<String, String> createMeetTime(@RequestBody NextMeetRequest request) {
        nextMeet.create(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Meet time created successfully");
        return response;
    }

    @GetMapping("/meet-time/{nationalId}")
    public List<NextMeet> getByNationalId(@PathVariable String nationalId) {
        return nextMeet.getByNationalId(nationalId);
    }

    @PutMapping("/update-meet-time/{id}")
    public Map<String, String> updateMeetTime(
            @PathVariable Long id,
            @RequestBody NextMeetRequest request
    ) {
        nextMeet.update(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Meet time updated successfully");
        return response;
    }

    @DeleteMapping("/delete-meet-time/{id}")
    public Map<String, String> deleteMeetTime(@PathVariable Long id) {
        nextMeet.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Meet time deleted successfully");
        return response;
    }

    @PostMapping("/add-test")
    public ResponseEntity<?> addTest(@RequestBody TestRequest request) {
        try {
            testService.addTest(request);
            return ResponseEntity.ok("Test added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all-test")
    public ResponseEntity<?> getAllTest() {
        return ResponseEntity.ok(testService.getAllTest());
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(testService.getById(id));
    }

    @GetMapping("/center/test/{name}")
    public ResponseEntity<?> getByDiagnosticCenter(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(
                testService.getByDiagnosticCenter(name)
        );
    }

    @PutMapping("/update-test/{id}")
    public ResponseEntity<?> updateTest(
            @PathVariable Long id,
            @RequestBody TestRequest request
    ) {
        testService.updateTest(id, request);
        return ResponseEntity.ok("Test updated successfully");
    }

    @DeleteMapping("/delete-test/{id}")
    public ResponseEntity<?> deleteTest(
            @PathVariable Long id
    ) {
        testService.deleteTest(id);
        return ResponseEntity.ok("Test deleted successfully");
    }

    @PostMapping("/add-duration")
    public ResponseEntity<?> addDuration(
            @RequestBody DurationRequest request
    ) {
        try {
            durationService.createDuration(request);
            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Duration added successfully"
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "message",
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/duration/{nationalId}")
    public ResponseEntity<List<Duration>> getDuration(
            @PathVariable String nationalId
    ) {
        return ResponseEntity.ok(
                durationService.getByNationalId(nationalId)
        );
    }

    @PutMapping("/update-duration/{id}")
    public ResponseEntity<?> updateDuration(
            @PathVariable Long id,
            @RequestBody DurationRequest request
    ) {
        durationService.updateDuration(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Duration updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-duration/{id}")
    public ResponseEntity<?> deleteDuration(
            @PathVariable Long id
    ) {
        durationService.deleteDuration(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Duration deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-doses")
    public ResponseEntity<String> addDose(@RequestBody DoseRequest request) {
        return ResponseEntity.ok(doseService.addDose(request));
    }

    @GetMapping("/doses/{nationalId}")
    public ResponseEntity<List<Dose>> getDoseByNationalId(@PathVariable String nationalId) {
        return ResponseEntity.ok(doseService.getDoseByNationalId(nationalId));
    }

    @PutMapping("/update-doses/{id}")
    public ResponseEntity<String> updateDose(
            @PathVariable Long id,
            @RequestBody DoseRequest request) {
        return ResponseEntity.ok(doseService.updateDose(id, request));
    }

    @DeleteMapping("/delete-doses/{id}")
    public ResponseEntity<String> deleteDose(@PathVariable Long id) {
        return ResponseEntity.ok(doseService.deleteDose(id));
    }

    @PostMapping("/patient/prescriptions")
    public ResponseEntity<String> createPrescription(@RequestBody PrescriptionRequest req) {
        prescription.createPrescription(req);
        return ResponseEntity.ok("Prescription created successfully");
    }

    @GetMapping("/patient/prescriptions/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescription.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/prescriptions/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(
                prescription.getPrescriptionsByPatientId(patientId)
        );
    }

    @PutMapping("/patient/prescriptions/{id}")
    public ResponseEntity<String> updatePrescription(
            @PathVariable Long id,
            @RequestBody PrescriptionRequest req
    ) {
        prescription.updatePrescription(id, req);
        return ResponseEntity.ok("Prescription updated successfully");
    }

    @PostMapping("/create/medicine_types")
    public ResponseEntity<String> createMedicineType(
            @RequestBody MedicineTypeRequest request
    ) {
        String response = medicineType.createMedicineType(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/medicine_types/{nationalId}")
    public ResponseEntity<List<MedicineType>> getMedicineType(
            @PathVariable String nationalId
    ) {
        return ResponseEntity.ok(medicineType.getMedicineType(nationalId));
    }

    @PutMapping("/update/medicine_types/{id}")
    public ResponseEntity<String> updateMedicineType(
            @PathVariable Long id,
            @RequestBody MedicineTypeRequest request
    ) {
        medicineType.updateMedicineType(id, request);
        return ResponseEntity.ok("Medicine type updated successfully");
    }

    @DeleteMapping("/delete/medicine_types/{id}")
    public ResponseEntity<String> deleteMedicineType(
            @PathVariable Long id
    ) {
        medicineType.deleteMedicineType(id);
        return ResponseEntity.ok("Medicine type deleted successfully");
    }

    @PostMapping("/create/dose-time")
    public String createDoseTime(@RequestBody DoseTimeRequest request) {
        return doseTime.createDoseTime(request);
    }

    @GetMapping("/dose-time/{nationalId}")
    public List<DoseTime> getDoseTime(@PathVariable String nationalId) {
        return doseTime.getDoseTimeByNationalId(nationalId);
    }

    @PutMapping("/update/dose-time/{id}")
    public String updateDoseTime(
            @PathVariable Long id,
            @RequestBody DoseTimeRequest request) {
        return doseTime.updateDoseTime(id, request);
    }

    @DeleteMapping("/delete/dose-time/{id}")
    public String deleteDoseTime(@PathVariable Long id) {
        return doseTime.deleteDoseTime(id);
    }
}