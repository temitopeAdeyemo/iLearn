package com.backend.iLearn.modules.course.validator;

import com.backend.iLearn.modules.course.dto.CreateCourseContentDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseContentValidator implements ConstraintValidator<AtLeastOneContentRequired, CreateCourseContentDto> {
    @Override
    public boolean isValid(CreateCourseContentDto dto, ConstraintValidatorContext context) {
        boolean hasVideos = dto.getVideos() != null && !dto.getVideos().isEmpty();
        boolean hasTexts = dto.getTexts() != null && !dto.getTexts().isEmpty();
        return hasVideos || hasTexts;
    }
}
