package com.backend.iLearn.modules.course.dto;

import lombok.Builder;

@Builder
public class CourseTextContentDto extends CourseContentDto{
    private String content;
}
