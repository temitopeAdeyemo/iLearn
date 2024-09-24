package com.backend.iLearn.modules.tutor.repository;

import com.backend.iLearn.modules.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {
}
