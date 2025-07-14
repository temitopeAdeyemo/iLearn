package com.backend.iLearn.modules.course.dto;

import com.backend.iLearn.modules.course.validator.AtLeastOneContentRequired;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AtLeastOneContentRequired
public class CreateCourseContentDto {
    @NotBlank(message = "course Id cannot be blank or empty.")
    private String courseId;

    @Valid
    private Set<CreateCourseVideoContentDto> videos;

    @Valid
    private Set<CreateCourseTextContentDto> texts;
}
