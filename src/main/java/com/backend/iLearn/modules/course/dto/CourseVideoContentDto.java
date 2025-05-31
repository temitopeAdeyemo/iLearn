package com.backend.iLearn.modules.course.dto;

import lombok.Builder;

@Builder
public class CourseVideoContentDto extends CourseContentDto {
    private String url;
}
