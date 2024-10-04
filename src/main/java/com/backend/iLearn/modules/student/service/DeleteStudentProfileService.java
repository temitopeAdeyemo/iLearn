package com.backend.iLearn.modules.student.service;

import com.backend.iLearn.modules.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteStudentProfileService {
    private final StudentRepository studentRepository;
    public void exec(UUID id) {
        this.studentRepository.deleteById(id);
    }
}
