package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.SeatRequest;
import com.myapp.public_hospital_backend.model.HospitalSeat;
import com.myapp.public_hospital_backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public HospitalSeat createSeat(SeatRequest request) {
        if (seatRepository.existsBySeatNo(request.getSeatNo())) {
            throw new RuntimeException("Seat '" + request.getSeatNo() + "' already exists!");
        }
        HospitalSeat seat = mapToEntity(request);
        return seatRepository.save(seat);
    }

    public List<HospitalSeat> getAllSeats() {
        return seatRepository.findAll();
    }

    public HospitalSeat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + id));
    }

    public HospitalSeat getSeatBySeatNo(String seatNo) {
        return seatRepository.findBySeatNo(seatNo)
                .orElseThrow(() -> new RuntimeException("Seat not found with seat number: " + seatNo));
    }

    public List<HospitalSeat> getSeatsByType(String type) {
        return seatRepository.findByType(type);
    }

    public List<HospitalSeat> getAvailableSeats() {
        return seatRepository.findByStatus(true);
    }

    public List<HospitalSeat> getUnavailableSeats() {
        return seatRepository.findByStatus(false);
    }

    public List<HospitalSeat> getAvailableSeatsByType(String type) {
        return seatRepository.findByTypeAndStatus(type, true);
    }

    public Long countAvailableSeatsByType(String type) {
        return seatRepository.countAvailableByType(type);
    }

    public HospitalSeat updateSeat(Long id, SeatRequest request) {
        HospitalSeat existing = getSeatById(id);
        if (!existing.getSeatNo().equals(request.getSeatNo())
                && seatRepository.existsBySeatNo(request.getSeatNo())) {
            throw new RuntimeException("Seat number already in use: " + request.getSeatNo());
        }
        existing.setType(request.getType());
        existing.setSeatNo(request.getSeatNo());
        existing.setPrice(request.getPrice());
        existing.setCurrency(request.getCurrency());
        existing.setStatus(request.getStatus());
        return seatRepository.save(existing);
    }

    public HospitalSeat toggleSeatStatus(Long id) {
        HospitalSeat seat = getSeatById(id);
        seat.setStatus(!seat.getStatus());
        return seatRepository.save(seat);
    }

    public void deleteSeat(Long id) {
        if (!seatRepository.existsById(id)) {
            throw new RuntimeException("Seat not found with ID: " + id);
        }
        seatRepository.deleteById(id);
    }

    private HospitalSeat mapToEntity(SeatRequest request) {
        HospitalSeat seat = new HospitalSeat();
        seat.setType(request.getType());
        seat.setSeatNo(request.getSeatNo());
        seat.setPrice(request.getPrice());
        seat.setCurrency(request.getCurrency());
        seat.setStatus(request.getStatus() != null ? request.getStatus() : true);
        return seat;
    }
}