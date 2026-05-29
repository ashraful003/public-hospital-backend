package com.myapp.public_hospital_backend.repository;

import com.myapp.public_hospital_backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByName(String name);

    List<Test> findByNameAndTestName(String name, String testName);
}
