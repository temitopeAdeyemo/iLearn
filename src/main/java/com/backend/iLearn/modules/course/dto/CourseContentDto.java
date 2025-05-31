package com.backend.iLearn.modules.course.dto;

import com.backend.iLearn.modules.course.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class CourseContentDto {
    private UUID id;

    @NotNull(message = "Sequence Number cannot be null")
    @NotBlank(message = "Sequence Number cannot be blank or empty.")
    @NotEmpty(message = "Sequence Number cannot be blank or empty")
    private int sequenceNumber;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Course course;

    private Date createdAt;
}
