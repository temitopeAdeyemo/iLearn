package com.backend.iLearn.modules.course.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public abstract class CreateCourseContentDto_ {
    @NotNull(message = "Sequence number cannot be null")
//    @NotBlank(message = "Sequence cannot be blank or empty.")\\
//    @NotEmpty(message = "Sequence cannot be blank or empty.")
    private Integer sequenceNumber;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be blank or empty.")
    @NotEmpty(message = "Title cannot be blank or empty.")
    @Size(min = 2, message = "Video title must be at least 2 characters long")
    private String title;
}
