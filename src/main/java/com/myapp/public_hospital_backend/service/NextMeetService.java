package com.myapp.public_hospital_backend.service;

import com.myapp.public_hospital_backend.dto.NextMeetRequest;
import com.myapp.public_hospital_backend.model.NextMeet;
import com.myapp.public_hospital_backend.repository.NextMeetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NextMeetService {
    private final NextMeetRepository repository;

    public NextMeetService(NextMeetRepository repository) {
        this.repository = repository;
    }

    public NextMeet create(NextMeetRequest request) {
        NextMeet nextMeet = new NextMeet();
        nextMeet.setNationalId(request.getNationalId());
        nextMeet.setDuration(request.getDuration());
        return repository.save(nextMeet);
    }

    public List<NextMeet> getByNationalId(String nationalId) {
        return repository.findByNationalId(nationalId);
    }

    public NextMeet update(Long id, NextMeetRequest request) {
        NextMeet nextMeet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Next Meet not found"));
        nextMeet.setNationalId(request.getNationalId());
        nextMeet.setDuration(request.getDuration());
        return repository.save(nextMeet);
    }

    public void delete(Long id) {
        NextMeet nextMeet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Next Meet not found"));
        repository.delete(nextMeet);
    }
}