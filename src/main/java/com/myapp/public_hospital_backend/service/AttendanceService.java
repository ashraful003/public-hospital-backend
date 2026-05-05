package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.model.Attendance;
import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.repository.AttendanceRepository;
import com.myapp.public_hospital_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepo;
    private final UserRepository userRepo;

    public AttendanceService(AttendanceRepository attendanceRepo, UserRepository userRepo) {
        this.attendanceRepo = attendanceRepo;
        this.userRepo = userRepo;
    }

    public User findUser(String nationalId) {
        return userRepo.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String checkIn(String nationalId) {
        User user = findUser(nationalId);
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepo
                .findByNationalIdAndDate(nationalId, today)
                .orElse(new Attendance(nationalId, user.getName(), today));
        if (attendance.getCheckIn() != null) {
            return "Already checked in today";
        }
        attendance.setCheckIn(LocalTime.now());
        attendanceRepo.save(attendance);
        user.setIsActive(true);
        userRepo.save(user);
        return "Check-in successful";
    }

    public String checkOut(String nationalId) {
        User user = findUser(nationalId);
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepo
                .findByNationalIdAndDate(nationalId, today)
                .orElseThrow(() -> new RuntimeException("No check-in found"));
        if (attendance.getCheckOut() != null) {
            return "Already checked out";
        }
        attendance.setCheckOut(LocalTime.now());
        attendanceRepo.save(attendance);
        user.setIsActive(false);
        userRepo.save(user);
        return "Check-out successful";
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAllByOrderByDateDesc();
    }

    public List<Attendance> getMyAttendance(String nationalId) {
        findUser(nationalId);
        return attendanceRepo.findByNationalIdOrderByDateDesc(nationalId);
    }
}