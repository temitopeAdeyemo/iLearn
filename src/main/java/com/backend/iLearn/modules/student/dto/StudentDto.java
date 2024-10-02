package com.backend.iLearn.modules.student.dto;

import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.course.entity.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private UUID id;

    private String firstName;

    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    private Set<Course> courses ;

    private Date createdAt;

    private Date updatedAt;
}
