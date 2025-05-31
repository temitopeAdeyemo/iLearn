package com.backend.iLearn.modules.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("VIDEO")
@Builder
@Data
@Entity
public class VideoContent extends CourseContent{

    @Column(name = "url", nullable = false)
    @NotNull(message = "Video URL cannot be null")
    @Size(min = 5, message = "URL must be at least 5 characters long")
    private String url;
}
