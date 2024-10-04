package com.backend.iLearn.modules.tutor.service;

import com.backend.iLearn.modules.tutor.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTutorProfileService {
    private final TutorRepository tutorRepository;
    public void exec(UUID id) {
        this.tutorRepository.deleteById(id);
    }
}
