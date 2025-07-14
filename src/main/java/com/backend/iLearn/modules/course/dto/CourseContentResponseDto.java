package com.backend.iLearn.modules.course.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CourseContentResponseDto {
    private UUID id;
    private int sequenceNumber;
    private String title;
    private String contentType;
    private String url;
    private String content;
    private String createdAt;
    private String updatedAt;
}
