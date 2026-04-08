package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNationalId(String nationalId);

    boolean existsByEmail(String email);
}
