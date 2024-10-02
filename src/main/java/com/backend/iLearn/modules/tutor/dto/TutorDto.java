package com.backend.iLearn.modules.tutor.dto;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.chat.entity.Chat;
import com.backend.iLearn.modules.course.entity.Course;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class TutorDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private User user;
    private Set<Course> courses;
    private Set<Chat> sentChats;
    private Set<Chat> receivedChats;
    private Date createdAt;
    private Date updatedAt;
}
