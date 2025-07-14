package com.backend.iLearn.modules.course.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
//@JsonIgnoreProperties(ignoreUnknown = false)
public class CreateCourseDto {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be blank or empty.")
    @NotEmpty(message = "Title cannot be blank or empty")
    private String title;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description cannot be blank or empty.")
    @NotEmpty(message = "Description cannot be blank or empty")
    private String description;

//    @Nullable
    @Valid
    private Set<CreateCourseVideoContentDto> videos;

//    @Nullable
    @Valid
    private Set<CreateCourseTextContentDto> texts;
}
