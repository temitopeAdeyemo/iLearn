package com.backend.iLearn.modules.tutor.service;

import com.backend.iLearn.common.exceptions.BadRequestException;
import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.modules.auth.dto.IdResponseDto;
import com.backend.iLearn.modules.course.repository.CourseRepository;
import com.backend.iLearn.modules.tutor.dto.TutorCourseDto;
import com.backend.iLearn.modules.tutor.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddTutorCoursesService {
    private final TutorRepository tutorRepository;
    private final CourseRepository courseRepository;

    public IdResponseDto exec(TutorCourseDto payload){
        var course = this.courseRepository.findById(UUID.fromString(payload.courseId)).orElseThrow(()->
             new NotFoundException("Course Not Found.")
        );

        var tutor = this.tutorRepository.findById(UUID.fromString(payload.tutorId)).orElseThrow(()->
                new NotFoundException("Tutor Not Found.")
        );

        var hasMatch = tutor.getCourses().stream().anyMatch(course1 -> {
            assert course1.getId() != null;
            return course1.getId().equals(course.getId());
        });

        if (hasMatch) {
            throw new BadRequestException("Tutor is already assigned to this course.");
        }

        course.setTutor(tutor);

        courseRepository.save(course);

        return IdResponseDto.builder().id(course.getId()).build();
    }
}
