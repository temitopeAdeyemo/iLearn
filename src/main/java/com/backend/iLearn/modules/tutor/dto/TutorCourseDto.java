package com.backend.iLearn.modules.tutor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TutorCourseDto {
    @NotNull(message = "Tutor Id must not be null")
    @NotBlank(message = "Tutor Id must not be blank")
    @NotEmpty(message = "Tutor Id must not be empty")
    public String tutorId;

    @NotNull(message = "Course Id must not be null")
    @NotBlank(message = "Course Id must not be blank")
    @NotEmpty(message = "Course Id must not be empty")
    public String courseId;
}
