package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.User;
import com.myapp.public_hospital_backend.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNationalId(String nationalId);

    List<User> findByRole(UserRole role);

    List<User> findByRoleAndIsActive(UserRole role, Boolean isActive);

    boolean existsByEmail(String email);
}
