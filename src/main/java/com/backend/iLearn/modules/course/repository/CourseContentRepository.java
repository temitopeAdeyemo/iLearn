package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.course.entity.CourseContent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CourseContentRepository extends JpaRepository<CourseContent, UUID> {
    List<CourseContent> findByCourse(Course course, Pageable pageable);
}
