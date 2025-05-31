package com.backend.iLearn.modules.course.repository;

import com.backend.iLearn.modules.course.entity.TextContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TextContentRepository extends JpaRepository<TextContent, UUID> {

}
