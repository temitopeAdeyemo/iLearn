package com.backend.iLearn.common.mapper;

import com.backend.iLearn.modules.admin.dto.AdminDto;
import com.backend.iLearn.modules.admin.entity.Admin;
import com.backend.iLearn.modules.auth.entity.User;

public class AdminMapper {
    public static AdminDto toDto(Admin admin) {
        if (admin == null) {
            return null;
        }

        return AdminDto.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .user(admin.getUser() != null ? admin.getUser() : null)
                .build();
    }

    public static Admin toEntity(AdminDto adminDTO, User user) {
        if (adminDTO == null) {
            return null;
        }

        return Admin.builder()
                .firstName(adminDTO.getFirstName())
                .lastName(adminDTO.getLastName())
                .user(user)
                .build();
    }
}
