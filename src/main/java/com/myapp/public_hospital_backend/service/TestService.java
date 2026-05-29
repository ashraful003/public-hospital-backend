package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.TestRequest;
import com.myapp.public_hospital_backend.model.Test;
import com.myapp.public_hospital_backend.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test addTest(TestRequest request) {
        List<Test> existing = testRepository.findByNameAndTestName(
                request.getName(),
                request.getTestName()
        );
        if (!existing.isEmpty()) {
            throw new RuntimeException("Test already exists");
        }
        Test test = new Test();
        test.setName(request.getName());
        test.setTestName(request.getTestName());
        test.setPrice(request.getPrice());
        test.setCurrency(request.getCurrency());
        return testRepository.save(test);
    }

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public Test getById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public List<Test> getByDiagnosticCenter(String name) {
        return testRepository.findByName(name);
    }

    public Test updateTest(Long id, TestRequest request) {
        Test test = getById(id);
        test.setName(request.getName());
        test.setTestName(request.getTestName());
        test.setPrice(request.getPrice());
        test.setCurrency(request.getCurrency());
        return testRepository.save(test);
    }

    public void deleteTest(Long id) {
        Test test = getById(id);
        testRepository.delete(test);
    }
}