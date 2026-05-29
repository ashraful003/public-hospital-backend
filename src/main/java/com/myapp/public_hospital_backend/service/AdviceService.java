package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.AdviceRequest;
import com.myapp.public_hospital_backend.model.Advice;
import com.myapp.public_hospital_backend.repository.AdviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceService {
    private final AdviceRepository repository;

    public AdviceService(AdviceRepository repository) {
        this.repository = repository;
    }

    public Advice createAdvice(AdviceRequest request) {
        Advice advice = new Advice();
        advice.setNationalId(request.getNationalId());
        advice.setTitle(request.getTitle());
        advice.setAdvice(request.getAdvice());
        return repository.save(advice);
    }

    public List<Advice> getByNationalId(String nationalId) {
        return repository.findByNationalId(nationalId);
    }

    public Advice updateAdvice(Long id, AdviceRequest request) {
        Advice advice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advice not found with id: " + id));
        advice.setTitle(request.getTitle());
        advice.setAdvice(request.getAdvice());
        return repository.save(advice);
    }

    public void deleteAdvice(Long id) {
        repository.deleteById(id);
    }
}