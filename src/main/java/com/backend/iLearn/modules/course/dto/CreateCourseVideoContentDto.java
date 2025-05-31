package com.backend.iLearn.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class CreateCourseVideoContentDto extends CreateCourseContentDto_ {
    @NotNull(message = "Url is required")
    @NotBlank(message = "Url cannot be blank or empty.")
    @NotEmpty(message = "Url cannot be blank or empty")
    private String url;
}
