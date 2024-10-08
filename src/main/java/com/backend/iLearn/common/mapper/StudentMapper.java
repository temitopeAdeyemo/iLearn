package com.backend.iLearn.common.mapper;

import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.student.dto.CreateStudentDto;
import com.backend.iLearn.modules.student.dto.StudentDto;
import com.backend.iLearn.modules.student.entity.Student;
import java.util.HashSet;
import java.util.Set;


public class StudentMapper {

    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        return StudentDto.builder()
                .id(student.getId())
                .user(student.getUser() != null ? student.getUser() : null)
                .courses(mapCoursesToIds(student.getCourses()))
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }

    public static Student toEntity(User user) {
        return Student.builder()
                .user(user)
                .build();
    }

    private static Set<Course> mapCoursesToIds(Set<Course> courses) {
        return courses != null ? new HashSet<>(courses) : null;
    }
}
