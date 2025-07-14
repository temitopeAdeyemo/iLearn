package com.backend.iLearn.modules.course.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CourseContentValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneContentRequired {
    String message() default "At least one of 'videos' or 'texts' must be provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
