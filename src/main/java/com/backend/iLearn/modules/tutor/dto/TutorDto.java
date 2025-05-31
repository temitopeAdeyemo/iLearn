package com.backend.iLearn.modules.tutor.dto;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class TutorDto {
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto user;
//    private Set<Course> courses;
//    private Set<Chat> sentChats;
//    private Set<Chat> receivedChats;
    private Date createdAt;
    private Date updatedAt;
}
