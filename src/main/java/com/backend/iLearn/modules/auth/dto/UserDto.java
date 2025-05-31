package com.backend.iLearn.modules.auth.dto;

import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.auth.entity.RoleEntity;
import com.backend.iLearn.modules.student.entity.Student;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Admin adminProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Tutor tutorProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Student studentProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<RoleEntity> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updatedAt;
}
