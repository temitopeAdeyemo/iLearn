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
public class CreateCourseTextContentDto extends CreateCourseContentDto_ {
    @NotNull(message = "Content cannot be null")
    @NotBlank(message = "Content cannot be blank or empty.")
    @NotEmpty(message = "Content cannot be blank or empty")
    private String content;

//    @NotNull(message = "Content cannot be null")
//    @NotBlank(message = "Content cannot be blank or empty.")
//    @NotEmpty(message = "Content cannot be blank or empty")
//    private String courseId;
}
