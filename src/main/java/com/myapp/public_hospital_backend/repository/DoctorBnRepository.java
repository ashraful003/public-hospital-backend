package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.DoctorBn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorBnRepository extends JpaRepository<DoctorBn, Long> {

    Optional<DoctorBn> findByDoctorBnId(String doctorBnId);

    boolean existsByDoctorBnId(String doctorBnId);

}
