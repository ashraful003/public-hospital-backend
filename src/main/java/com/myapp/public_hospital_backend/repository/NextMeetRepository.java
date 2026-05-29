package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.NextMeet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NextMeetRepository extends JpaRepository<NextMeet, Long> {
    List<NextMeet> findByNationalId(String nationalId);
}
