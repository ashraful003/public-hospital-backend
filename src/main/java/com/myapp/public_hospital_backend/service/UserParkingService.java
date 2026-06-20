package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.UserParkingRequest;
import com.myapp.public_hospital_backend.model.Parking;
import com.myapp.public_hospital_backend.model.UserParking;
import com.myapp.public_hospital_backend.repository.ParkingRepository;
import com.myapp.public_hospital_backend.repository.UserParkingRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserParkingService {
    private final UserParkingRepository userParkingRepository;
    private final ParkingRepository parkingRepository;

    public UserParkingService(
            UserParkingRepository userParkingRepository,
            ParkingRepository parkingRepository
    ) {
        this.userParkingRepository = userParkingRepository;
        this.parkingRepository = parkingRepository;
    }

    public Map<String, Object> createUserParking(UserParkingRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (userParkingRepository.existsByVehicleNoAndExitTimeIsNull(request.getVehicleNo())) {
            response.put("message", "Vehicle already parked.");
            return response;
        }
        Parking parking = parkingRepository.findById(request.getParkingId())
                .orElseThrow(() ->
                        new RuntimeException("Parking slot not found"));
        if (!parking.getIsActive()) {
            response.put("message", "Parking slot already occupied");
            return response;
        }
        UserParking userParking = new UserParking();
        userParking.setPatientId(request.getPatientId());
        userParking.setPatientName(request.getPatientName());
        userParking.setMobileNo(request.getMobileNo());
        userParking.setVehicleNo(request.getVehicleNo());
        userParking.setVehicleType(request.getVehicleType());
        userParking.setParking(parking);
        userParking.setFloor(parking.getFloor());
        userParking.setParkingNo(parking.getParkingNo());
        userParking.setParkingFee(parking.getParkingFee());
        userParking.setEntryTime(LocalDateTime.now());
        userParking.setIsActive(true);
        userParking.setStatus("ACTIVE");
        parking.setIsActive(false);
        parkingRepository.save(parking);
        userParkingRepository.save(userParking);
        response.put("message", "Vehicle parked successfully");
        return response;
    }

    public List<UserParking> getAllUserParking() {
        return userParkingRepository.findAll();
    }

    public List<UserParking> getActiveUserParking() {
        return userParkingRepository.findByExitTimeIsNull();
    }

    public List<UserParking> getPatientParking(String patientId) {
        return userParkingRepository.findByPatientId(patientId);
    }

    public Map<String, Object> exitVehicle(String vehicleNo) {
        Map<String, Object> response = new LinkedHashMap<>();
        UserParking userParking = userParkingRepository
                .findByVehicleNoAndExitTimeIsNull(vehicleNo)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        LocalDateTime exitTime = LocalDateTime.now();
        userParking.setExitTime(exitTime);
        Duration duration = Duration.between(userParking.getEntryTime(), exitTime);
        long minutes = duration.toMinutes();
        long totalHours = (long) Math.ceil(minutes / 60.0);
        if (totalHours == 0) {
            totalHours = 1;
        }
        double totalAmount = totalHours * userParking.getParkingFee();
        userParking.setTotalHours(totalHours);
        userParking.setTotalAmount(totalAmount);
        userParking.setIsActive(false);
        userParking.setStatus("EXIT");
        Parking parking = userParking.getParking();
        parking.setIsActive(true);
        parkingRepository.save(parking);
        userParkingRepository.save(userParking);
        response.put("message", "Vehicle exited successfully");
        return response;
    }
}