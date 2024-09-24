package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
