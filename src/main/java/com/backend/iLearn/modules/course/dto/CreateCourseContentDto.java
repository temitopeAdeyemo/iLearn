package com.backend.iLearn.modules.course.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CreateCourseContentDto {
    private String courseId;

    @Nullable
    private Set<CreateCourseVideoContentDto> videos;

    @Nullable
    private Set<CreateCourseTextContentDto> texts;
}
