package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.CourseVideos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseVideosRepository extends JpaRepository<CourseVideos, UUID> {
}
