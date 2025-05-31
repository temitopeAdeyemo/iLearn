package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.CourseContent;
import com.backend.iLearn.modules.course.entity.VideoContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoContentRepository extends JpaRepository<VideoContent, UUID>{
}