package com.backend.iLearn.common.mapper;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.backend.iLearn.modules.course.entity.Course;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.entity.Tutor;

import java.util.HashSet;
import java.util.Set;

public class TutorMapper {

    public static TutorDto toDto(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        var user = tutor.getUser();
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return TutorDto.builder()
                .id(tutor.getId())
//                .firstName(tutor.getFirstName())
//                .lastName(tutor.getLastName())
                .user(tutor.getUser() != null ? userDto : null)
//                .courses(mapCoursesToIds(tutor.getCourses()))
                .createdAt(tutor.getCreatedAt())
                .updatedAt(tutor.getUpdatedAt())
                .build();
    }

    public static Tutor toEntity(User user) {


        return Tutor.builder()
                .user(user)
                .build();
    }

    private static Set<Course> mapCoursesToIds(Set<Course> courses) {
        return courses != null ? new HashSet<>(courses) : null;
    }
}
