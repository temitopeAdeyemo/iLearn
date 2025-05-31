package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseContentRepository extends JpaRepository<CourseContent, UUID> {
}
