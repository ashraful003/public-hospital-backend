package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByName(String name);

    List<Test> findByNameAndTestName(String name, String testName);

    Optional<Test> findFirstByTestNameIgnoreCase(String testName);
}
