package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.ParkingRequest;
import com.myapp.public_hospital_backend.model.Parking;
import com.myapp.public_hospital_backend.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public Map<String, Object> createParking(ParkingRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (parkingRepository.existsByParkingNo(request.getParkingNo())) {
            response.put("message", "Parking number already exists");
            return response;
        }
        Parking parking = new Parking();
        parking.setFloor(request.getFloor());
        parking.setParkingNo(request.getParkingNo());
        parking.setParkingFee(request.getParkingFee() != null ? request.getParkingFee() : 0.0);
        parking.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);
        parkingRepository.save(parking);
        response.put("message", "Parking created successfully");
        return response;
    }

    public String updateParking(Long id, ParkingRequest request) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
        parking.setFloor(request.getFloor());
        parking.setParkingNo(request.getParkingNo());
        parking.setParkingFee(request.getParkingFee());
        parking.setIsActive(request.getIsActive());
        parkingRepository.save(parking);
        return "SUCCESS";
    }

    public Parking getParkingById(Long id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
    }

    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }

    public void deleteParking(Long id) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
        parkingRepository.delete(parking);
    }
}