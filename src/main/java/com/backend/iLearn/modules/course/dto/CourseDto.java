package com.backend.iLearn.modules.course.dto;

import com.backend.iLearn.modules.course.entity.CourseContent;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class CourseDto {
    private UUID id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 250 characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CourseContent> videos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Tutor tutor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Student> students;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;
}
