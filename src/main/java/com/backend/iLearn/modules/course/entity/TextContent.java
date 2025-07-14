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
@Data
@Builder
@DiscriminatorValue("TEXT")
@Entity
public class TextContent extends CourseContent {
    @Column(name = "content", columnDefinition = "TEXT")
//    @NotNull(message = "Content cannot be null")
    private String content;
}
