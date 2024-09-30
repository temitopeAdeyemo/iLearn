package com.backend.iLearn.modules.student.repository;

import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
