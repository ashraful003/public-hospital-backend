package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    List<Advice> findByNationalId(String nationalId);
}
