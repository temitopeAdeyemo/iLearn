package com.backend.iLearn.common.mapper;

import com.backend.iLearn.modules.auth.dto.UserDto;
import com.backend.iLearn.modules.auth.entity.User;
import com.backend.iLearn.modules.auth.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert from User entity to UserDTO
    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(mapRolesToNames(user.getRoles()))
                .adminProfile(user.getAdminProfile() != null ? user.getAdminProfile() : null)
                .tutorProfile(user.getTutorProfile() != null ? user.getTutorProfile() : null)
                .studentProfile(user.getStudentProfile() != null ? user.getStudentProfile() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    // Convert from UserDTO to User entity
    public static User toEntity(UserDto userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    // Helper method to map roles to their names
    private static Set<RoleEntity> mapRolesToNames(Set<RoleEntity> roles) {
        return roles != null ? new HashSet<>(roles) : null;
    }
}

